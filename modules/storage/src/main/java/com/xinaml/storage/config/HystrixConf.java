package com.xinaml.storage.config;

import com.xinaml.common.hystrix.HystrixCommandAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author: [lgq]
 * @Date: [19-5-29 下午12:56]
 * @Description: 断路器配置
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
@Aspect
@Component
public class HystrixConf extends HystrixCommandAspect {


}
