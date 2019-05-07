package com.xinaml.storage.ser;

import com.xinaml.storage.entity.Storage;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午5:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public interface StorageSer {
    default Storage get(String name){
        return null;
    }
}
