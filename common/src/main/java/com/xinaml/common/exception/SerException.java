/**
 * 业务层 自定义异常
 *
 * @author lgq
 * @date 2018/4/15
 **/
package com.xinaml.common.exception;

/**
 * @Author: [lgq]
 * @Date: [19-5-30 上午8:58]
 * @Description: 自定义业务异常
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class SerException extends RuntimeException {
    public SerException(String msg) {
        super(msg);
    }

    public SerException() {
        super();
    }

    public SerException(String msg, int code) {
        super(code + "@" + msg);
    }
}
