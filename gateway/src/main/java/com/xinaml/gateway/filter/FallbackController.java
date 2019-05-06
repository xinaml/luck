package com.xinaml.gateway.filter;

import com.xinaml.gateway.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午2:31]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
@RestController
public class FallbackController {
    @GetMapping("/fallback")
    public Result fallback() {
        Result response = new Result();
        response.setCode("100");
        response.setMsg("服务暂时不可用!");
        return response;
    }
}
