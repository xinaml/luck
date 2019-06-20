package com.xinaml.user.act;

import com.xinaml.user.ser.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-6-20 上午9:13]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RestController
public class LoginAct {
    @Autowired
    private UserSer userSer;

    @PostMapping("login")
    public String login(String username, String password) {
        String token = userSer.login(username, password);
        return token;
    }

    @PostMapping("logout")
    public String logout(String token) {
        return userSer.logout(token);
    }
}
