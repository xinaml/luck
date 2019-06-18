package com.xinaml.order.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author: [lgq]
 * @Date: [19-6-17 下午4:05]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO {
    private String username;
    private String password;
    private Double account;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }
}
