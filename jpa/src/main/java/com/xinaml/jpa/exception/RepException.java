/**
 * rep层 自定义异常
 *
 * @author lgq
 * @date 2018/4/15
 **/
package com.xinaml.jpa.exception;

public class RepException extends RuntimeException {

    private Throwable throwable;

    public RepException(String msg) {
        super(msg);
    }

    public RepException(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
