package com.xinaml.user.config.auth.service;

import com.xinaml.user.entity.User;
import com.xinaml.user.ser.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Service
public class UserDetailsService extends BaseUserDetailService {
    @Autowired
    private UserSer userSer;

    @Override
    protected User getUser(String username) {
        return userSer.findByUsername(username);
    }

}
