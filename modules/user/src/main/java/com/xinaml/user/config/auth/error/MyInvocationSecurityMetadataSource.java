package com.xinaml.user.config.auth.error;

import com.xinaml.user.entity.Menu;
import com.xinaml.user.ser.MenuSer;
import com.xinaml.user.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 权限数据元
 */
@Component
public class MyInvocationSecurityMetadataSource implements
        FilterInvocationSecurityMetadataSource {



    private HashMap<String, Collection<ConfigAttribute>> map = null;


    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (null == map ||map.size()==0 ) {
            loadResourceDefine();
        }
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl = "";
        for (Iterator<String> iter = map.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            matcher = new AntPathRequestMatcher(resUrl);
            if (matcher.matches(request)) {
                return map.get(resUrl);
            }
        }
        throw new AccessDeniedException("没有访问权限");
    }



    /**
     * 加载权限表中所有权限
     */
    public void loadResourceDefine() {
        map = new HashMap<>();
        Collection<ConfigAttribute> array;
        ConfigAttribute cfg;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserVO u = (UserVO) authentication.getPrincipal();
        Map<String,List<String>> rolePermissions = u.getPermissions();//角色，权限列表
        for (GrantedAuthority authority : u.getAuthorities()) {
            array = new ArrayList<>();
            cfg = new SecurityConfig(authority.getAuthority());
            array.add(cfg);
            List<String> permissions = rolePermissions.get(authority.getAuthority());////获取角色资源
            for (String permission : permissions) {
                map.put(permission, array);
            }
        }
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
