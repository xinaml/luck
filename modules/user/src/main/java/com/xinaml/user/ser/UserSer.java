package com.xinaml.user.ser;

import com.xinaml.jpa.ser.Ser;
import com.xinaml.user.dto.UserDTO;
import com.xinaml.user.entity.User;
import com.xinaml.user.to.RegisterTO;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午5:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public interface UserSer extends Ser<User, UserDTO> {

    default User findByUsername(String username) {
        return null;
    }

    default User findByMobile(String mobile) {
        return null;
    }

    default User save(User user) {
        return null;
    }

    default String subtract(String userId, Double account) {
        return null;
    }

    default String register(RegisterTO to){
        return null;
    }

    default String login(String username ,String password){
        return null;
    }

    default String logout(String token ){
        return null;
    }
}
