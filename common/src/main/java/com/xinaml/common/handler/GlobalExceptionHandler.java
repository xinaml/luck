package com.xinaml.common.handler;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.xinaml.common.constant.CodeConst;
import com.xinaml.common.constant.MsgConst;
import com.xinaml.common.exception.RepException;
import com.xinaml.common.exception.SerException;
import com.xinaml.common.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.netflix.hystrix.exception.HystrixRuntimeException.FailureType;
import java.util.List;

/**
 * @Author: [lgq]
 * @Date: [19-5-28 上午9:30]
 * @Description:全局异常处理
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class GlobalExceptionHandler {

    /**
     * 未识别的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(), MsgConst.SERVER_ERROR);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleException(HttpRequestMethodNotSupportedException e) {
        e.printStackTrace();
        return new Result(HttpStatus.METHOD_NOT_ALLOWED.value(), "请求方法【"+e.getMethod()+"】不支持!");
    }

    /**
     * 持久化异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RepException.class)
    public Result handleException(RepException e) {
        e.printStackTrace();
        return new Result(CodeConst.REP_CODE, e.getMessage());
    }

    /**
     * 业务异常
     *
     * @param e
     * @return
     */

    @ExceptionHandler(SerException.class)
    public Result handleException(SerException e) {
        e.printStackTrace();
        return new Result(CodeConst.SER_CODE, e.getMessage());
    }

    /**
     * 参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleIllegalParamException(MethodArgumentNotValidException e) {
        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = MsgConst.PARAM_ERROR;
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        Result result = new Result(CodeConst.PARAM_CODE);
        result.setMsg(tips);
        return result;
    }

    /**
     * 熔断器异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HystrixRuntimeException.class)
    public Result hystrixRuntimeException(HystrixRuntimeException e) {
        e.printStackTrace();
        String msg = MsgConst.HYSTRIX_ERROR;
        Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        FailureType type = e.getFailureType();
        if(type.equals(FailureType.TIMEOUT)){
            msg = MsgConst.HYSTRIX_TIMEOUT;
            code = HttpStatus.GATEWAY_TIMEOUT.value();
        }
        return new Result(code, msg);
    }

}
