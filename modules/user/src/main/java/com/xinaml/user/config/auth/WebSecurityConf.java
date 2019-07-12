package com.xinaml.user.config.auth;

import com.xinaml.user.config.filter.MyFilterSecurityInterceptor;
import com.xinaml.user.config.auth.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * security适配器
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //允许api上使用权限的PreAuthorize注解
public class WebSecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    /**
     * 获取用户的验证配置类
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 加密配置
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 需要配置这个支持password模式
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    /**
     * 权限管理器 AuthorizationServerConf认证中心需要的AuthenticationManager需要
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/hello","/login","/info","/favicon.ico","/oauth/**").permitAll() //指定不需要验证的页面，其他的默认会跳转到登录页
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()  //支持表单提交
                .failureForwardUrl("/error")   //自定也错误
                .defaultSuccessUrl("/info")  //登录成功页面
                .and().logout()
                .permitAll();
                http.csrf().disable().addFilterAfter(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);


    }
    @Value("#{'${auth.filter.url}'.split(',')}")
    private String[] filterUrls;



}
