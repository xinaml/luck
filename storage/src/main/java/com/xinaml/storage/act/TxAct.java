package com.xinaml.storage.act;

import com.xinaml.storage.ser.StorageSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-14 下午3:23]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RestController
public class TxAct {
    @Autowired
    private StorageSer storageSer;

    @GetMapping("subtract")
    public Boolean subtract(String name,Integer count){
        storageSer.subtract(name,count);
        return true;
    }
}
