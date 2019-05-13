package com.xinaml.storage.ser;

import com.xinaml.jpa.ser.Ser;
import com.xinaml.storage.dto.StorageDTO;
import com.xinaml.storage.entity.Storage;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午5:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public interface StorageSer extends Ser<Storage,StorageDTO> {
    default Storage get(String name){
        return null;
    }

    default Storage save(Storage storage){
        return null;
    }
}
