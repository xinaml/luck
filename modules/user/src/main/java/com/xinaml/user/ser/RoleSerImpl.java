package com.xinaml.user.ser;

import com.xinaml.jpa.ser.ServiceImpl;
import com.xinaml.user.dto.RoleDTO;
import com.xinaml.user.entity.Role;
import com.xinaml.user.rep.RoleRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Service
public class RoleSerImpl extends ServiceImpl<Role,RoleDTO> implements RoleSer {
    @Autowired
    private RoleRep roleRep;
    @Override
    public List<Role> findByUserId(String userId) {
        return roleRep.findByUserId(userId);
    }
}
