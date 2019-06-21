package com.xinaml.user.config.auth;

import com.xinaml.user.config.auth.error.AuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源认证中心
 */
@Configuration
@EnableResourceServer
public class ResourceServerConf extends ResourceServerConfigurerAdapter {
    @Autowired
    private AuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.authenticationEntryPoint(authExceptionEntryPoint);
    }
}
