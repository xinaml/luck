package com.xinaml.order.act;

import com.xinaml.order.vo.StorageVO;
import com.xinaml.order.feign.StorageFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 上午8:56]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RefreshScope
@RestController
public class OrderAct {
    @Autowired
    private StorageFeign storageService;

    /**
     * 远程调用
     *
     * @param name
     * @return
     */
    @GetMapping("get")
    public StorageVO get(String name) {
        StorageVO rs = storageService.get(name);
        return rs;
    }

    /**
     * 超时测试(断路器)
     *
     * @return
     */
    @GetMapping("timeout")
    public String timeout() {
        String rs = storageService.timeout();
        return rs;
    }

    /**
     * 测试读取远程配置
     */
    @Value("${foo}")
    private String foo;
    @RequestMapping("/foo")
    public String from() {
        return this.foo;
    }
}
