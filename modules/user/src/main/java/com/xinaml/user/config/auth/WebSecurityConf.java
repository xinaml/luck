package com.xinaml.user.config.auth;

import com.xinaml.user.config.auth.filter.LoginFilter;
import com.xinaml.user.config.auth.handler.LoginAuthFailureHandler;
import com.xinaml.user.config.auth.handler.LoginAuthSuccessHandler;
import com.xinaml.user.config.auth.service.UserDetailsService;
import com.xinaml.user.config.filter.MyFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * security适配器
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //允许api上使用权限的PreAuthorize注解
public class WebSecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
    @Autowired
    private LoginAuthSuccessHandler loginAuthSuccessHandler;
    @Autowired
    private LoginAuthFailureHandler loginAuthFailureHandler;
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
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/phoneLogin", "/hello", "/login", "/info", "/favicon.ico", "/oauth/**").permitAll() //指定不需要验证的页面，其他的默认会跳转到登录页
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()  //支持表单提交
                .failureForwardUrl("/error")   //自定也错误
                .defaultSuccessUrl("/info")  //登录成功页面
                .and().logout()
                .permitAll();
        http.csrf().disable().addFilterAfter(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                .addFilterAt(getLoginFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Value("#{'${auth.filter.url}'.split(',')}")
    private String[] filterUrls;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(userDetailsService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public LoginFilter getLoginFilter() {
        LoginFilter filter = new LoginFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(loginAuthSuccessHandler);
        filter.setAuthenticationFailureHandler(loginAuthFailureHandler);
        return filter;
    }


}
