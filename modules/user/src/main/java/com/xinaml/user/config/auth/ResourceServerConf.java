package com.xinaml.user.config.auth;

import com.xinaml.user.config.auth.error.SecurityAuthenticationEntryPoint;
import com.xinaml.user.config.auth.error.WebResponseExceptionTrans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

/**
 * 资源认证中心
 */
@Configuration
@EnableResourceServer
public class ResourceServerConf extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        OAuth2AuthenticationEntryPoint authenticationEntryPoint = new SecurityAuthenticationEntryPoint();
        authenticationEntryPoint.setExceptionTranslator(new WebResponseExceptionTrans());
        resources
                .authenticationEntryPoint(authenticationEntryPoint);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                // 定义的不存在access_token时候响应
                .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                .and()
                .authorizeRequests().antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable();
    }


}
