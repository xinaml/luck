package com.xinaml.user.ser;


import com.xinaml.jpa.ser.Ser;
import com.xinaml.user.dto.MenuDTO;
import com.xinaml.user.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public interface MenuSer extends Ser<Menu, MenuDTO> {
    default List<Menu> findByRoleId(String roleId){
        return new ArrayList<>(0);
    }
}
