package com.xinaml.order.config;

import com.xinaml.common.aspect.RepeatSubmitAspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Aspect
@Component
public class RepeatSubmitConf extends RepeatSubmitAspect {
}
