package com.xinaml.order.act.ser;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午6:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
@FeignClient(value = "storage", fallback = StorageFallBack.class)
public interface StorageService {
    //服务中方法的映射路径
    @GetMapping("get")
    String get(@RequestParam("name") String name);
}
