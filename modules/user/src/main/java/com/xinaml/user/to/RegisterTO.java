package com.xinaml.user.to;

import com.xinaml.jpa.verify.ADD;

import javax.validation.constraints.NotBlank;

/**
 * @Author: [lgq]
 * @Date: [19-6-20 上午9:15]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xianml]
 */
public class RegisterTO {
    @NotBlank(message = "账号不能为空！",groups = ADD.class)
    private String username;
    @NotBlank(message = "密码不能为空！",groups = ADD.class)
    private String password;
    @NotBlank(message = "确认密码不能为空！",groups = ADD.class)
    private String rePassword;

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

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
}
