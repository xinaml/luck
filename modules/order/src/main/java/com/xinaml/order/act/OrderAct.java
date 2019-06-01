package com.xinaml.order.act;

import com.xinaml.common.exception.SerException;
import com.xinaml.common.hystrix.HystrixCommand;
import com.xinaml.order.feign.StorageFeign;
import com.xinaml.order.vo.StorageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;

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
    @Autowired(required = false)
    private StorageFeign storageFeign;

    /**
     * token传递
     *
     * @return
     */
    @GetMapping("token")
    public String token(HttpServletRequest request) {
        String token = request.getHeader("token");
        System.out.println(token);
        return storageFeign.token();
    }

    @PostMapping("add")
    public String add(HttpServletRequest request) {
        String token = request.getHeader("token");
        return "add";
    }

    /**
     * 负载均衡测试
     *
     * @return
     */
    @GetMapping("port")
    public String port() {
        return storageFeign.port();
    }

    /**
     * 远程调用
     *
     * @param name
     * @return
     */
    @HystrixCommand
    @GetMapping("get")
    public StorageVO get(@NotBlank(message = "名字不能为空！") String name) {
        StorageVO rs = storageFeign.get(name);
        return rs;
    }

    /**
     * 超时测试(断路器)
     *
     * @return
     */
    @HystrixCommand
    @GetMapping("timeout")
    public String timeout() {
        String rs = storageFeign.timeout();
        return rs;
    }

    /**
     * 测试读取远程配置
     */
    @Value("${foo}")
    private String foo;

    @GetMapping("/foo")
    public String from() {
        if (true) {
            throw new SerException("xxxx");
        }
        return this.foo;
    }
}
