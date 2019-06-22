package com.xinaml.user.ser;

import com.xinaml.jpa.ser.Ser;
import com.xinaml.user.dto.RoleDTO;
import com.xinaml.user.entity.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public interface RoleSer extends Ser<Role, RoleDTO> {
    default List<Role> findByUserId(String userId){
        return new ArrayList<>(0);
    }
}
