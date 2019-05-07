package com.xinaml.storage.ser;

import com.xinaml.storage.entity.Storage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午5:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Service
public class StorageSerImpl implements StorageSer {
    @Value("${server.port}")
    private String port;

    @Override
    public Storage get(String name) {
        Storage storage = new Storage();
        storage.setName(name);
        storage.setCount(1);
        storage.setPrice(111.0);
        storage.setPort(port);
        return storage;
    }
}
