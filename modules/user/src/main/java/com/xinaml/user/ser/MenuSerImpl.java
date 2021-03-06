package com.xinaml.user.ser;

import com.xinaml.jpa.ser.ServiceImpl;
import com.xinaml.user.dto.MenuDTO;
import com.xinaml.user.entity.Menu;
import com.xinaml.user.rep.MenuRep;
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
public class MenuSerImpl extends ServiceImpl<Menu,MenuDTO>implements MenuSer {
    @Autowired
    private MenuRep menuRep;

    @Override
    public List<Menu> findByRoleId(String roleId) {
        return menuRep.findByRoleId(roleId);
    }
}
