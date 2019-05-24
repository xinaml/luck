package com.xinaml.order.feign;

import com.xinaml.order.vo.StorageVO;
import org.springframework.stereotype.Component;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午6:02]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Component
public class StorageFallBack implements StorageFeign {
    @Override
    public StorageVO get(String name) {
        return null;
    }

    @Override
    public String timeout() {
        return null;
    }

    @Override
    public Boolean subtract(String name, Integer count) {
        return false;
    }
}
