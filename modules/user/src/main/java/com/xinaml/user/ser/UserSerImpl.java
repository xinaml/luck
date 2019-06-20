package com.xinaml.user.ser;

import com.xinaml.common.exception.SerException;
import com.xinaml.jpa.dto.RT;
import com.xinaml.jpa.ser.ServiceImpl;
import com.xinaml.user.dto.UserDTO;
import com.xinaml.user.entity.User;
import com.xinaml.user.rep.UserRep;
import com.xinaml.user.to.RegisterTO;
import com.xinaml.user.util.PwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @Author: [lgq]
 * @Date: [19-5-6 下午5:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Service
public class UserSerImpl extends ServiceImpl<User, UserDTO> implements UserSer {
    @Value("${server.port}")
    private String port;
    @Autowired
    private UserRep userRep;

    @Override
    public User findByUsername(String username) {
        return userRep.findByUsername(username);
    }

    @Transactional
    @Override
    public User save(User user) {
        user.setCreateDate(LocalDateTime.now());
        return super.save(user);
    }

    @Transactional
    @Override
    public String subtract(String userId, Double account) {
        UserDTO dto = new UserDTO();
        dto.addRT(RT.eq("id", userId));
        User user = findOne(dto);
        user.setAccount(user.getAccount() - account);
        super.save(user);
        return "success";
    }

    @Override
    public String register(RegisterTO to) {
        if(to.getPassword().equals(to.getRePassword())){
            User user = new User();
            user.setUsername(to.getUsername());
            String password =PwdUtil.encode(to.getPassword());
            user.setPassword(password);
            user.setCreateDate(LocalDateTime.now());
            User u = super.save(user);
            return u.getUsername();
        }else {
            throw new SerException("密码不比配！");
        }
    }

    @Override
    public String login(String username, String password) {
        User user = findByUsername(username);
        if(null!=user){
            if(PwdUtil.verify(password,user.getPassword())){//通过
                return user.getUsername();
            }
        }

       throw new SerException("账号或密码错误！");
    }

    @Override
    public String logout(String token) {

        return "退出失败！";
    }
}
