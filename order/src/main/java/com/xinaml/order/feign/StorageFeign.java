package com.xinaml.order.feign;

import com.xinaml.order.vo.StorageVO;
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
public interface StorageFeign {
    //服务中方法的映射路径
    @GetMapping("get")
    StorageVO get(@RequestParam("name") String name);

    //服务中方法的映射路径
    @GetMapping("timeout")
    String timeout();

    /**
     * 减库存（测试事务）
     * @param name
     * @param count
     */
    @GetMapping("subtract")
    Boolean subtract(@RequestParam("name") String name,@RequestParam("count") Integer count);

}
