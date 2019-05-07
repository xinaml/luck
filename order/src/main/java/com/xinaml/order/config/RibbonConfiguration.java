package com.xinaml.order.config;

import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午4:58]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Configuration
public class RibbonConfiguration {
    @Bean
    public IRule ribbonRule(){
        // new RandomRule() 随机轮询
        //最大可用策略
        return new BestAvailableRule();
    }
}
