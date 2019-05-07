package com.xinaml.gateway.limiter;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

/**
 * @Author: [lgq]
 * @Date: [19-5-7 上午10:39]
 * @Description: 限流器配置
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class LimiterConfigure {

    /**
     *  IP 地址来限流
     * @return
     */
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(exchange.getRequest().getRemoteAddress().getHostName());

    }
}