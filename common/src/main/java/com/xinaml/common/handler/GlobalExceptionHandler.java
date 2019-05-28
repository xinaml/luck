package com.xinaml.common.handler;

import com.netflix.hystrix.exception.HystrixRuntimeException;
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
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return new Result(HttpStatus.INTERNAL_SERVER_ERROR.value(),"服务器异常！");
    }

    /**
     * 参数异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleIllegalParamException(MethodArgumentNotValidException e) {

        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String tips = "参数不合法";
        if (errors.size() > 0) {
            tips = errors.get(0).getDefaultMessage();
        }
        Result result = new Result(HttpStatus.PAYMENT_REQUIRED.value());
        result.setMsg(tips);

        return result;
    }

    /**
     * 熔断器异常
     * @param e
     * @return
     */
    @ExceptionHandler(HystrixRuntimeException.class)
    public Result hystrixRuntimeException(HystrixRuntimeException e) {
        e.printStackTrace();
        String msg="远程服务调用失败！";
        Integer code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        if(e.getMessage().indexOf("timed-out")!=-1){
            msg="服务暂时不可用！";
            code =HttpStatus.GATEWAY_TIMEOUT.value();
        }
        Result result = new Result(code,msg);
        return result;
    }

}
