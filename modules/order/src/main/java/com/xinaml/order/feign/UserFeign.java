package com.xinaml.order.feign;

import com.xinaml.order.vo.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午6:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@FeignClient(value = "user")
public interface UserFeign {

    //服务中方法的映射路径
    @GetMapping("timeout")
    String timeout();

    /**
     * 减存款（测试事务）
     *
     * @param userId
     * @param account
     */
    @RequestMapping(value = "subtract",method = RequestMethod.POST)
    String subtract(@RequestParam("userId") String userId, @RequestParam("account") Double account);

    //远程调用测试
    @RequestMapping(value = "get",method = RequestMethod.GET)
    UserVO get(@RequestParam("userId") String userId);

}
