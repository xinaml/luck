package com.xinaml.user.rep;

import com.xinaml.jpa.dao.JapRep;
import com.xinaml.user.dto.MenuDTO;
import com.xinaml.user.entity.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public interface MenuRep extends JapRep<Menu, MenuDTO> {

}
