package com.xinaml.gateway.filter;

import com.xinaml.gateway.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午2:31]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RestController
public class FallbackController {
    @GetMapping("fallback")
    public Result fallback() {
        Result result = new Result();
        result.setCode(HttpStatus.GATEWAY_TIMEOUT.value());
        result.setMsg("服务暂时不可用!");
        return result;
    }


}
