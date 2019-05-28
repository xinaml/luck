package com.xinaml.order.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
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
public class RibbonConf {
    @Bean
    public IRule ribbonRule() {
        // new BestAvailableRule() 最大可用策略
        //随机轮询
        return new RandomRule();
    }
}
