package com.xinaml.user.config.auth;

import com.xinaml.common.auth.MyAccessDeniedHandler;
import com.xinaml.common.auth.MyAuthenticationEntryPoint;
import com.xinaml.user.config.auth.error.WebResponseExceptionTrans;
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
        MyAuthenticationEntryPoint authenticationEntryPoint = new MyAuthenticationEntryPoint();
        authenticationEntryPoint.setExceptionTranslator(webResponseExceptionTrans);
        resources
                .authenticationEntryPoint(authenticationEntryPoint);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MyAuthenticationEntryPoint authenticationEntryPoint = new MyAuthenticationEntryPoint();
        authenticationEntryPoint.setExceptionTranslator(webResponseExceptionTrans);
        http
                .csrf().disable()
                .exceptionHandling()
                // 定义的不存在access_token时候响应
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(new MyAccessDeniedHandler())
                .and()
                .authorizeRequests().antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable();
    }


}
