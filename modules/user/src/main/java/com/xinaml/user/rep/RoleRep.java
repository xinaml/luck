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
    @Query(value = "select b.* from lk_user_role a , lk_role b where a.user_id=?1 and a.role_id=b.id",nativeQuery = true)
    List<Role> findByUserId(String userId);
}
