package com.xinaml.storage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

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
    @Primary
    @Bean
    public RemoteTokenServices tokenServices() {
        final RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl("http://localhost:8083/oauth/check_token");
        tokenService.setClientId("webApp");
        tokenService.setClientSecret("123456");
        return tokenService;
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
