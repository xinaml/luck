package com.xinaml.storage.act;

import com.xinaml.storage.entity.Storage;
import com.xinaml.storage.ser.StorageSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-14 下午3:23]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RequestMapping("api")
@RestController
public class TxAct {
    @Autowired
    private StorageSer storageSer;

    @PostMapping("subtract")
    public String subtract(String name, Integer count) {
        String rs = storageSer.subtract(name, count);
        return rs;
    }


    @GetMapping("get")
    public Storage get(String name) {
        Storage storage = storageSer.findAll().get(0);
        return storage;
    }

}
