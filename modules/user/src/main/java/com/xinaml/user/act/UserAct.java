package com.xinaml.user.act;

import com.xinaml.common.redis.RedisRep;
import com.xinaml.common.utils.UserUtil;
import com.xinaml.user.dto.RoleDTO;
import com.xinaml.user.entity.Role;
import com.xinaml.user.entity.User;
import com.xinaml.user.ser.RoleSer;
import com.xinaml.user.ser.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import java.security.Principal;
import java.util.List;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 上午8:56]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@RefreshScope
@RestController
public class UserAct {
    @Autowired
    private UserSer userSer;
    @Autowired
    private RoleSer roleSer;
    @GetMapping("get")
    public User user(String userId) {
        return userSer.findAll().get(0);
    }
    @GetMapping("role")
    public List<Role> roles(HttpServletRequest  request) {
       Object token = request.getAttribute("access_token");
       if(null!=token){
           RedisRep redisRep = RedisRep.build();
           String v = redisRep.get(token.toString());
           System.out.println();
       }
        return roleSer.findAll();
    }
    @PostMapping("save")
    public User save(User user) {
        return userSer.save(user);
    }

    @RequestMapping("/info")
    public Principal user(Principal user) {
        return user;
    }

}
