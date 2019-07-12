package com.xinaml.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-7-12 上午9:13]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.changbei]
 */
@RestController
public class Act {
    @GetMapping({"/", ""})
    public String succ() {

        return "success";
    }

    @GetMapping({"hello"})
    public String index() {

        return "hello";
    }
}
