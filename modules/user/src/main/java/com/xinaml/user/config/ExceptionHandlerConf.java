package com.xinaml.user.config;

import com.xinaml.common.handler.GlobalExceptionHandler;
import com.xinaml.common.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: [lgq]
 * @Date: [19-5-28 上午9:30]
 * @Description: 异常捕获配置
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RestControllerAdvice
public class ExceptionHandlerConf extends GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public Result accessDeniedException(AccessDeniedException e) {
        e.printStackTrace();
        return new Result(HttpStatus.FORBIDDEN.value(), "没有访问权限!");
    }
}
