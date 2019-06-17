package com.xinaml.user.ser;

import com.xinaml.jpa.ser.Ser;
import com.xinaml.user.dto.UserDTO;
import com.xinaml.user.entity.User;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午5:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public interface UserSer extends Ser<User, UserDTO> {
    default User get(String name) {
        return null;
    }

    default User save(User user) {
        return null;
    }

    default String subtract(String userId,Double account) {
        return null;
    }
}
