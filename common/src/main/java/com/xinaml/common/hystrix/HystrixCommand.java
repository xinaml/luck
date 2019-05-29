package com.xinaml.common.hystrix;

import java.lang.annotation.*;

/**
 * @Author: [lgq]
 * @Date: [19-5-29 下午12:54]
 * @Description: 涉及远程调用的加该注解
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HystrixCommand {

    String groupKey() default "groupKey";

    String commandKey() default "commandKey";

    String threadPoolKey() default "threadPoolKey";

    int timeOutSecond() default 3;

    boolean timeOutEnabled() default true;

}
