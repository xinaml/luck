package com.xinaml.order.act;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 上午8:56]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RestController
public class OrderAct {
    @GetMapping("test")
    public String test() {
        return "test";
    }
}
