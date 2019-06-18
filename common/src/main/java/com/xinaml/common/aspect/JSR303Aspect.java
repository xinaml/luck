package com.xinaml.common.aspect;

import com.xinaml.common.constant.CodeConst;
import com.xinaml.common.result.Result;
import com.xinaml.common.utils.ResponseUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @Author: [lgq]
 * @Date: [19-6-1 下午2:01]
 * @Description: 数据校验
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class JSR303Aspect {

    /**
     * 只拦截act
     */
    @Pointcut("(@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            " ||@annotation(org.springframework.web.bind.annotation.PostMapping)" +
            " ||@annotation(org.springframework.web.bind.annotation.PutMapping)" +
            " ||@annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            " ||@annotation(org.springframework.web.bind.annotation.PatchMapping))&&" + "execution(* com.xinaml.*.act..*.*(..)) ")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        BindingResult result = null;
        MethodSignature methodSignature = (MethodSignature) joinPoint
                .getSignature();
        Method method = methodSignature.getMethod();
        Annotation[][] argAnnotations = method.getParameterAnnotations();
        Object[] args = joinPoint.getArgs();//获取参数值
        if (method.getName().equals("errorHtml")) {//springmvc 错误请求
            return joinPoint.proceed(args);
        }
        boolean exists = false; //是否包含验证注解
        for (Annotation[] annotations : argAnnotations) {
            if (annotations.length > 0 && annotations[0] instanceof Validated) {
                exists = true;
                break;
            }
        }
        if (exists) {//检测是否带注解
            for (int i = 0, len = args.length; i < len; i++) {
                Object object = args[i];
                if (null != object) {
                    if (null == result && object instanceof BindingResult) {
                        result = (BindingResult) object;
                    }

                }
            }
            if (null != result && writeResult(result)) {//数据校验不通过
                return null;
            }
        }

        int index = 0;
        for (Annotation[] ants : argAnnotations) {
            if (ants.length > 0) {
                Object obj = args[index++];
                for (Annotation an : ants) {
                    if (an instanceof NotBlank || an instanceof NotNull) {
                        if (null == obj || StringUtils.isBlank(obj.toString())) {
                            String msg = an.toString();
                            msg = StringUtils.substringAfter(msg, "message=");
                            msg = StringUtils.substringBefore(msg, ",");
                            writeResult(msg);//数据校验不通过
                            return null;
                        }
                    }
                }
            }


        }
        return joinPoint.proceed(args);
    }

    protected boolean writeResult(BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            if (null != fieldErrors && fieldErrors.size() > 0) {
                Result rs = new Result();
                rs.setCode(CodeConst.PARAM_CODE);
                rs.setMsg(fieldErrors.get(0).getDefaultMessage());
                ResponseUtil.writeData(rs);
                return true;
            }
        }
        return false;
    }

    protected void writeResult(String msg) {
        Result rs = new Result();
        rs.setCode(CodeConst.PARAM_CODE);
        rs.setMsg(msg);
        ResponseUtil.writeData(rs);
    }
}
