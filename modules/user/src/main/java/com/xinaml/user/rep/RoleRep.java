package com.xinaml.user.rep;

import com.xinaml.jpa.dao.JapRep;
import com.xinaml.user.dto.RoleDTO;
import com.xinaml.user.dto.UserDTO;
import com.xinaml.user.entity.Role;
import com.xinaml.user.entity.User;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public interface RoleRep extends JapRep<Role, RoleDTO> {

}
