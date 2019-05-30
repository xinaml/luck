package com.xinaml.common.exception;

/**
 * @Author: [lgq]
 * @Date: [19-5-30 上午8:58]
 * @Description: 自定义持久化异常
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class RepException extends RuntimeException{
    public RepException(String msg) {
        super(msg);
    }

    public RepException() {
        super();
    }

    public RepException(String msg, int code) {
        super(code + "@" + msg);
    }
}
