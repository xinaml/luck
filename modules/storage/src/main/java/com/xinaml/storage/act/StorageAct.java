package com.xinaml.storage.act;

import com.xinaml.storage.entity.Storage;
import com.xinaml.storage.ser.StorageSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 上午8:56]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RefreshScope
@RestController
public class StorageAct {
    @Autowired
    private StorageSer storageSer;

    @Value("${server.port}")
    private String port;

    @GetMapping("port")
    public String port() {
        return port;
    }

    @GetMapping("token")
    public String get(HttpServletRequest request) {
        String token = request.getHeader("token");
        return token;
    }


    @GetMapping("get")
    public Storage get(String name) {
        Storage storage = storageSer.findAll().get(0);
        return storage;
    }

    @GetMapping("save")
    public Storage save(Storage storage) {
        return storageSer.save(storage);
    }

    @GetMapping("timeout")
    public String timeout() {
        try {
            //睡2秒，网关Hystrix3秒超时，会触发熔断降级操作
            Thread.sleep(3500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "timeout";
    }

    /**
     * 读取远程配置
     */
    @Value("${foo}")
    private String foo;

    @RequestMapping("/foo")
    public String from() {
        return this.foo;
    }
}
