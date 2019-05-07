package com.xinaml.order.act;

import com.xinaml.order.ser.StorageService;
import com.xinaml.order.entity.Storage;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private StorageService feignService;

    /**
     * 远程调用
     * @param name
     * @return
     */
    @GetMapping("get")
    public Storage get(String name) {
        Storage rs = feignService.get(name);
        return rs;
    }
}
