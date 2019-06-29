package com.xinaml.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @Author: [lgq]
 * @Date: [19-6-28 上午11:14]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
@Configuration
@EnableResourceServer
public class OAuth2ResourceConf extends ResourceServerConfigurerAdapter {
    @Autowired
    LoadBalancerClient loadBalancerClient;
//    @Primary
//    @Bean
//    public RemoteTokenServices tokenServices() {
//
//        ServiceInstance instance = loadBalancerClient.choose("user");
//        URI uri = instance.getUri();
//        // 令牌申请的地址
//        String authUrl = uri + "/oauth/check_token";
//        final RemoteTokenServices tokenService = new RemoteTokenServices();
//        tokenService.setCheckTokenEndpointUrl(authUrl);
//        tokenService.setClientId("webApp");
//        tokenService.setClientSecret("123456");
//        return tokenService;
//    }

    /**
     * 通过redis去校验token
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(redisTokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12); // token有效期自定义设置，默认12小时
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);//默认30天，这里修改
        return tokenServices;
    }


    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    RedisTokenStore redisTokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }


    /**
     * 除了api,其他都拦截
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests().antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable();
    }
}
