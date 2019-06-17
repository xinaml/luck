package com.xinaml.user.ser;

import com.xinaml.jpa.dto.RT;
import com.xinaml.jpa.ser.ServiceImpl;
import com.xinaml.user.dto.UserDTO;
import com.xinaml.user.entity.User;
import org.springframework.beans.factory.annotation.Value;
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

    @Override
    public User get(String name) {
        return findAll().get(0);
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
        dto.addRT(RT.eq("id",userId));
        User user = findOne(dto);
        user.setAccount(user.getAccount()-account);
        super.save(user);
        return "success";
    }
}
