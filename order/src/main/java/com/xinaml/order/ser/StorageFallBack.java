package com.xinaml.order.ser;

import com.xinaml.order.entity.Storage;
import org.springframework.stereotype.Component;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午6:02]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Component
public class StorageFallBack implements StorageService {
    @Override
    public Storage get(String name) {
        return null;
    }
}
