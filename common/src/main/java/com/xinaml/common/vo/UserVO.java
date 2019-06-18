package com.xinaml.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @Author: [lgq]
 * @Date: [19-6-18 下午3:18]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserVO {
    private String id;
    private String username;
    private String account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
