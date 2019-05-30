package com.xinaml.common.result;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午2:13]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class Result {
    /**
     * 0：正常数据
     * -1：业务异常
     * -2：持久化异常
     * 其他：服务器异常
     */
    private Integer code=0;
    private String msg;
    private Object data;

    public Result() {

    }

    public Result(Integer code) {
        this.code = code;
    }

    public Result(Integer code, String msg) {
        this.msg = msg;
        this.code = code;

    }

    public Result(Integer code, String msg, Object data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
