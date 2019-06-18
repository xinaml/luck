package com.xinaml.common.hystrix;

import com.xinaml.common.constant.MsgConst;
import com.xinaml.common.result.Result;
import com.xinaml.common.utils.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Method;

/**
 * @Author: [lgq]
 * @Date: [19-5-29 下午12:56]
 * @Description: 断路器aop
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class HystrixCommandAspect {
    HystrixCommandAdvice advice = new HystrixCommandAdvice();

    @Pointcut("@annotation(com.xinaml.common.hystrix.HystrixCommand)")
    public void execMethod() {

    }

    @Around("execMethod()")
    public void around(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HystrixCommand action = method.getAnnotation(HystrixCommand.class);
        advice.setCommandName(action.commandKey());
        advice.setGroupName(action.groupKey());
        advice.setThreadKey(action.threadPoolKey());
        advice.setTimeOutSecond(action.timeOutSecond());
        advice.setTimeOutEnable(action.timeOutEnabled());
        Object object = advice.runCommand(joinPoint);
        if ("FALLBACK".equals(object)) {
            Result result = new Result();
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMsg(MsgConst.HYSTRIX_ERROR);
            try {
                ResponseUtil.writeData(result);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else { //直接返回结果
            ResponseUtil.writeData(object);
        }
    }

}
