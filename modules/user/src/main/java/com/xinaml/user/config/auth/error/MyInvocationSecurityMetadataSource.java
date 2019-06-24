package com.xinaml.user.config.auth.error;

import com.xinaml.user.entity.Menu;
import com.xinaml.user.entity.Role;
import com.xinaml.user.entity.User;
import com.xinaml.user.ser.MenuSer;
import com.xinaml.user.ser.RoleSer;
import com.xinaml.user.ser.UserDetailsServiceImpl;
import com.xinaml.user.ser.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;

/**
 * 权限数据元
 */
@Component
public class MyInvocationSecurityMetadataSource implements
        FilterInvocationSecurityMetadataSource {


    @Autowired
    private MenuSer menuSer;
    @Autowired
    private RoleSer roleSer;
    @Autowired
    private UserSer userSer;
    private HashMap<String, Collection<ConfigAttribute>> map =null;

    /**
     * 加载权限表中所有权限
     */
    public void loadResourceDefine(){
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        org.springframework.security.core.userdetails.User u= (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        User user =userSer.findByUsername(u.getUsername());
        List<Role> roles =roleSer.findByUserId(user.getId());
        for(Role role:roles){
            List<Menu> permissions = menuSer.findByRoleId(role.getId());
            array = new ArrayList<>();
            cfg = new SecurityConfig("ROLE_"+role.getName());
            array.add(cfg);
            for(Menu permission : permissions) {
                map.put(permission.getUrl(), array);
            }
        }
    }

    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(map ==null) {
            loadResourceDefine();
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl="";
        for(Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if(matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        String url=request.getRequestURI();
        if(url.startsWith("/oauth")||url.equals("/register")||url.equals("/login")){
            return null;
        }
        throw new AccessDeniedException("没有访问权限");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>(0);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
