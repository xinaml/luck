package com.xinaml.storage.act;

import com.xinaml.storage.entity.Storage;
import com.xinaml.storage.ser.StorageSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 上午8:56]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RestController
public class StorageAct {
    @Autowired
    private StorageSer storageSer;

    @GetMapping("get")
    public Storage get(String name) {
        return storageSer.get(name);
    }
}
