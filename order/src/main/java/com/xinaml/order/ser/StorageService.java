package com.xinaml.order.ser;

import com.xinaml.order.entity.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午6:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@FeignClient(value = "storage", fallback = StorageFallBack.class)
public interface StorageService {
    //服务中方法的映射路径
    @GetMapping("get")
    Storage get(@RequestParam("name") String name);

    //服务中方法的映射路径
    @GetMapping("timeout")
    String timeout();
}
