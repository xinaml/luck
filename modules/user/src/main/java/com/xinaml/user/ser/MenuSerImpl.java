package com.xinaml.user.ser;

import com.xinaml.user.rep.MenuRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Service
public class MenuSerImpl implements MenuSer {
    @Autowired
    private MenuRep menuRep;


}
