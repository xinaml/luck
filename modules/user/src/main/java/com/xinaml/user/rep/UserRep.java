package com.xinaml.user.rep;

import com.xinaml.jpa.dao.JapRep;
import com.xinaml.user.dto.UserDTO;
import com.xinaml.user.entity.User;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public interface UserRep extends JapRep<User, UserDTO> {
    User findByUsername(String username);
    User findByMobile(String mobile);
}
