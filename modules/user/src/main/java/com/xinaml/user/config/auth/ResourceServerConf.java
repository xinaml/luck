package com.xinaml.user.config.auth;

import com.xinaml.user.config.auth.error.MyAccessDeniedHandler;
import com.xinaml.user.config.auth.error.MyAuthenticationEntryPoint;
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
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        authenticationEntryPoint.setExceptionTranslator(webResponseExceptionTrans);
        resources
                .authenticationEntryPoint(authenticationEntryPoint);

    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                // 定义的不存在access_token时候响应
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(myAccessDeniedHandler)
                .and()
                .authorizeRequests().antMatchers("/oauth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic().disable();
    }


}
