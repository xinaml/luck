package com.xinaml.user.act;

import com.xinaml.user.ser.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("subtract")
    public Boolean subtract(String userId, Double account) {
        userSer.subtract(userId, account);
        return true;
    }
}
