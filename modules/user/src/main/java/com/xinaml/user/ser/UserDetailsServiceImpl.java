package com.xinaml.user.ser;

import com.xinaml.user.entity.Menu;
import com.xinaml.user.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserSer userSer;
    @Autowired
    private RoleSer roleSer;
    /**
     * 启动刷新token授权类型，会判断用户是否还是存活的
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.xinaml.user.entity.User user = userSer.findByUsername(username);
        if(null!=user){
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            boolean enabled = true; // 可用性 :true:可用 false:不可用
            boolean accountNonExpired = true; // 过期性 :true:没过期 false:过期
            boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
            boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定
            List<Role> roles =roleSer.findByUserId(user.getId());
            for (Role role : roles) {
                //角色必须是ROLE_开头，可以在数据库中设置
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
                grantedAuthorities.add(grantedAuthority);
            }
            UserDetails details = new User(user.getUsername(), user.getPassword(),
                    enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
            return details;
        }else {
            throw new  UsernameNotFoundException("账号或密码错误！");
        }

    }


}
