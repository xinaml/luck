package com.xinaml.order.feign;

import com.xinaml.order.vo.StorageVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午6:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@FeignClient(value = "storage")
public interface StorageFeign {

    @GetMapping("token")
    String token();

    //负载均衡测试
    @GetMapping("port")
    String port();

    //远程调用测试
    @GetMapping("get")
    StorageVO get(@RequestParam("name") String name);

    //超时测试
    @GetMapping("timeout")
    String timeout();

    /**
     * 减库存（测试事务）
     * @param name
     * @param count
     */
    @PostMapping("subtract")
    String subtract(@RequestParam("name") String name,@RequestParam("count") Integer count);

}
