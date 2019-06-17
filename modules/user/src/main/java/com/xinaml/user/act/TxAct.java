package com.xinaml.user.act;

import com.xinaml.user.entity.User;
import com.xinaml.user.ser.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-14 下午3:23]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RestController
public class TxAct {
    @Autowired
    private UserSer userSer;

    @PostMapping("subtract")
    public String subtract( String userId,  Double account) {
       String rs = userSer.subtract(userId, account);
        return rs;
    }

    @GetMapping("get")
    public User user(String userId) {
        return userSer.findAll().get(0);
    }
}
