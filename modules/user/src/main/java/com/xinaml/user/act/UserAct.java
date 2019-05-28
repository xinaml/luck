package com.xinaml.user.act;

import com.xinaml.user.entity.User;
import com.xinaml.user.ser.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 上午8:56]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RefreshScope
@RestController
public class UserAct {
    @Autowired
    private UserSer userSer;

    @GetMapping("get")
    public User get(String name) {
        return userSer.findAll().get(0);
    }

    @GetMapping("save")
    public User save(User user) {
        return userSer.save(user);
    }

}