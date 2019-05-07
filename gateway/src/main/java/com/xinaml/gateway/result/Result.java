package com.xinaml.gateway.result;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午2:13]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
public class Result {
    private Integer code;
    private String msg;
    private Object data;

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
