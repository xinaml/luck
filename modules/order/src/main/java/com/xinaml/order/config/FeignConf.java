package com.xinaml.order.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: [lgq]
 * @Date: [19-5-7 下午2:21]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Configuration
public class FeignConf {
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default();
    }

}
