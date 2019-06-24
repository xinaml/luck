package com.xinaml.user.config.auth;

import com.xinaml.user.config.auth.error.WebResponseExceptionTrans;
import com.xinaml.user.ser.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

/**
 * 授权认证中心
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConf extends AuthorizationServerConfigurerAdapter {
    /**
     * 权限验证控制器
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * 数据源， 默认为spring中配置的datasource
     */
    @Autowired
    private DataSource dataSource;
    /**
     * 保存token的时候需要
     */
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private WebResponseExceptionTrans webResponseExceptionTrans;


    @Bean // 客户端保存在数据库
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }


    /**
     * 存token
     *
     * @return
     */
    @Bean
    RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 用来配置客户端详情服务（ClientDetailsService），
     * 客户端详情信息在这里进行初始化，  数据库在进行client_id 与 client_secret验证时   会使用这个service进行验证
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

    /**
     * token失效时间
     *
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setClientDetailsService(clientDetailsService());
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
        return tokenServices;
    }

    /**
     * 用来配置授权（auth）以及令牌（token）的访问端点和令牌服务   核心配置  在启动时就会进行配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(redisTokenStore())
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
        endpoints.tokenServices(defaultTokenServices());
        endpoints.exceptionTranslator(webResponseExceptionTrans);//认证异常
    }


    /**
     * 用来配置令牌端点(Token Endpoint)的安全约束.
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.tokenKeyAccess("permitAll()");
        security.checkTokenAccess("isAuthenticated()");
        security.allowFormAuthenticationForClients();
    }
}
