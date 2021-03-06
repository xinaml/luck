package com.xinaml.common.aspect;

import com.xinaml.common.constant.CodeConst;
import com.xinaml.common.constant.MsgConst;
import com.xinaml.common.redis.RedisRep;
import com.xinaml.common.result.Result;
import com.xinaml.common.utils.ResponseUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @Author: [lgq]
 * @Date: [19-6-1 下午2:01]
 * @Description: 表单重复提交校验
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class RepeatSubmitAspect {
    @Autowired
    private RedisRep redisRep;

    /**
     * 只拦截act
     */
    @Pointcut("(@annotation(org.springframework.web.bind.annotation.PostMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PatchMapping))&&" + "execution(* com.xinaml.*.act..*.*(..)) ")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Exception{
        String key = "";
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
        HttpServletRequest request = attributes.getRequest();
        key = sessionId + "-" + request.getServletPath() + LocalDateTime.now().getSecond();
        if (!redisRep.exists(key)) {// 是否重复提交
            redisRep.put(key, "", 2, TimeUnit.SECONDS);//缓存2秒
        } else {
            redisRep.expire(key, -1, TimeUnit.SECONDS);
            Result rs = new Result(CodeConst.REPEAT_CODE, MsgConst.REPEAT_ERROR);
            ResponseUtil.writeData(rs);
            return null;
        }
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw (Exception) e; //抛出异常由全局异常处理器处理
        }
    }


}
