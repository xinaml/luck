package com.xinaml.storage.ser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午5:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
@Service
public class StorageSerImpl implements StorageSer {
    @Value("${server.port}")
    private String port;

    @Override
    public String get() {
        return "hello storage" + port;
    }
}
