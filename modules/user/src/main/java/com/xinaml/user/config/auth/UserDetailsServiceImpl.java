package com.xinaml.user.config.auth;

import com.xinaml.common.vo.UserVO;
import com.xinaml.user.entity.Menu;
import com.xinaml.user.entity.Role;
import com.xinaml.user.entity.User;
import com.xinaml.user.ser.MenuSer;
import com.xinaml.user.ser.RoleSer;
import com.xinaml.user.ser.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

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
    @Autowired
    private MenuSer menuSer;

    /**
     * 启动刷新token授权类型，会判断用户是否还是存活的
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userSer.findByUsername(username);
        if (null != user) {
            Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
            boolean enabled = true; // 可用性 :true:可用 false:不可用
            boolean accountNonExpired = true; // 过期性 :true:没过期 false:过期
            boolean credentialsNonExpired = true; // 有效性 :true:凭证有效 false:凭证无效
            boolean accountNonLocked = true; // 锁定性 :true:未锁定 false:已锁定
            List<Role> roles = roleSer.findByUserId(user.getId());
            Map<String, List<String>> rolePermissions = new HashMap<>();
            for (Role role : roles) {
                String roleName = "ROLE_" + role.getName();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(roleName);
                grantedAuthorities.add(grantedAuthority);
                List<Menu> menus = menuSer.findByRoleId(role.getId());
                List<String> permissions = new ArrayList<>();
                for (Menu menu : menus) {
                    permissions.add(menu.getUrl());
                }
                rolePermissions.put(roleName, permissions);
            }
            UserVO userVO = new UserVO(user.getUsername(), user.getPassword(),
                    enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuthorities);
            userVO.setId(user.getId());
            userVO.setPermissions(rolePermissions);
            return userVO;
        } else {
            throw new UsernameNotFoundException("账号或密码错误！");
        }

    }


}
