package com.xinaml.user.act;

import com.xinaml.jpa.verify.ADD;
import com.xinaml.user.ser.UserSer;
import com.xinaml.user.to.RegisterTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
public class RegisterAct {
    @Autowired
    private UserSer userSer;
    @PostMapping("register")
    public String register(@Validated(ADD.class) RegisterTO to, BindingResult rs) {
        String token = userSer.register(to);
        return token;
    }
}
