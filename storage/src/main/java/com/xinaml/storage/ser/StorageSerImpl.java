package com.xinaml.storage.ser;

import com.xinaml.jpa.dto.BaseDTO;
import com.xinaml.jpa.ser.ServiceImpl;
import com.xinaml.storage.dto.StorageDTO;
import com.xinaml.storage.entity.Storage;
import com.xinaml.storage.rep.StorageRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午5:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Service
public class StorageSerImpl extends ServiceImpl<Storage,StorageDTO> implements StorageSer {
    @Value("${server.port}")
    private String port;
    @Autowired
    private StorageRep storageRep;

    @Override
    public Storage get(String name) {
        Storage storage = new Storage();
        storage.setName(name);
        storage.setCount(1);
        storage.setPrice(111.0);
        return storage;
    }
    @Transactional
    @Override
    public Storage save(Storage storage) {
        storage.setCreateDate(LocalDateTime.now());
        return storageRep.save(storage);
    }
}
