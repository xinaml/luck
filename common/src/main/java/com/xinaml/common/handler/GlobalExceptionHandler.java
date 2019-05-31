package com.xinaml.common.handler;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.xinaml.common.constant.MsgConst;
import com.xinaml.common.exception.RepException;
import com.xinaml.common.exception.SerException;
import com.xinaml.common.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * 全局异常处理
 *
 * @Author: [lgq]
 * @Date: [19-5-28 上午9:30]
 * @Description:
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

    /**
     * 持久化异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RepException.class)
    public Result handleException(RepException e) {
        e.printStackTrace();
        return new Result(-2, e.getMessage());
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
        return new Result(-1, e.getMessage());
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
        Result result = new Result(HttpStatus.PAYMENT_REQUIRED.value());
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
        if (e.getMessage().indexOf("timed-out") != -1) {
            msg = MsgConst.HYSTRIX_TIMEOUT;
            code = HttpStatus.GATEWAY_TIMEOUT.value();
        }
        Result result = new Result(code, msg);
        return result;
    }

}
