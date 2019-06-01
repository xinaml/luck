package com.xinaml.storage.config;

import com.xinaml.common.aspect.JSR303Aspect;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: [lgq]
 * @Date: [19-5-31 下午4:55]
 * @Description: 数据校验配置
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Order(2)
@Aspect
@Component
public class JSR303Conf extends JSR303Aspect {
}
