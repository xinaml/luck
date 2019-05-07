package com.xinaml.order.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: [lgq]
 * @Date: [19-5-7 下午2:21]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
@Configuration
public class FeignConf {

    @Bean
    public Retryer feignRetryer() {
//        Retryer retryer = new Retryer.Default(100, 1000, 4);//自定义重试次数
        return new Retryer.Default();
    }

}
