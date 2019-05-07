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
    @GetMapping("fallback")
    public Result fallback() {
        Result result = new Result();
        result.setCode(-2);
        result.setMsg("服务暂时不可用!");
        return result;
    }


}
