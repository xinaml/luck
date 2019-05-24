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
@FeignClient(value = "user", fallback = UserFallBack.class)
public interface UserFeign {
    //服务中方法的映射路径
    @GetMapping("get")
    StorageVO get(@RequestParam("name") String name);

    //服务中方法的映射路径
    @GetMapping("timeout")
    String timeout();

    /**
     * 减存款（测试事务）
     * @param userId
     * @param account
     */
    @GetMapping("subtract")
    Boolean subtract(@RequestParam("userId") String userId,@RequestParam("account") Double account);

}
