package com.xinaml.order.act;

import com.xinaml.common.hystrix.HystrixCommand;
import com.xinaml.common.utils.UserUtil;
import com.xinaml.order.feign.StorageFeign;
import com.xinaml.order.vo.StorageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
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
public class OrderAct {
    private static Logger LOG = LoggerFactory.getLogger(OrderAct.class);

    @Autowired(required = false)
    private StorageFeign storageFeign;

    /**
     * log
     *
     * @return
     */
    @GetMapping("log")
    public String log() {
        LOG.info("this is info");
        LOG.error("this is error");
        LOG.warn("this is warn");
        LOG.debug("this is debug");
        return "log";
    }

    @GetMapping("get")
    public StorageVO get(String name) {
        return storageFeign.get(name);
    }

    @GetMapping("user")
    public UserVO user(String token) {
        return UserUtil.getUser(token);
    }


    /**
     * token传递
     *
     * @return
     */
    @HystrixCommand
    @GetMapping("token")
    public String token(HttpServletRequest request) {
        String token = request.getHeader("token");
        LOG.info(token);
        return storageFeign.token();
    }


    /**
     * 负载均衡测试
     *
     * @return
     */
    @HystrixCommand
    @GetMapping("port")
    public String port() {
        return storageFeign.port();
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
        return this.foo;
    }
}
