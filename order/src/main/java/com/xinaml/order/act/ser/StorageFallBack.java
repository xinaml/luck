package com.xinaml.order.act.ser;

import org.springframework.stereotype.Component;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午6:02]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
@Component
public class StorageFallBack implements StorageService {
    @Override
    public String get(String name) {
        return "error";
    }
}
