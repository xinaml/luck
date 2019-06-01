package com.xinaml.user.config;

import com.xinaml.common.hystrix.HystrixCommandAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: [lgq]
 * @Date: [19-5-29 下午12:56]
 * @Description: 断路器配置
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Order(3)
@Aspect
@Component
public class HystrixConf extends HystrixCommandAspect {


}
