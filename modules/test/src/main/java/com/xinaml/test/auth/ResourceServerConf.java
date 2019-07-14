package com.xinaml.test.auth;

import com.xinaml.common.auth.MyAccessDeniedHandler;
import com.xinaml.common.auth.MyAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源认证中心适配器
 */
@Configuration
@EnableResourceServer
public class ResourceServerConf extends ResourceServerConfigurerAdapter {
    @Autowired
    private WebResponseExceptionTrans webResponseExceptionTrans;


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {


    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().antMatchers("/","/hello","/login","/oauth/**").permitAll() //指定不需要验证的页面，其他的默认会跳转到登录页
                .and()
                .httpBasic().disable();
    }

}
