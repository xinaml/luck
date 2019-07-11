package com.xinaml.user.config.filter;

import com.xinaml.user.config.auth.MyAccessDecisionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import java.io.IOException;

/**
 * 添加权限数据源到过滤器
 */
@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    public void setMyAccessDecisionManager(MyAccessDecisionManager myAccessDecisionManager) {
        super.setAccessDecisionManager(myAccessDecisionManager);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    /**
     * fi里面有一个被拦截的url
     * 里面调用MyInvocationSecurityMetadataSource的getAttributes(Object object)这个方法获取fi对应的所有权限
     * 再调用MyAccessDecisionManager的decide方法来校验用户的权限是否足够
     *
     * @param fi
     * @throws IOException
     * @throws ServletException
     */


    @Value("${enable.auth}")
    private Boolean enableAuth; //是否开启权限控制

    @Value("#{'${auth.filter.url}'.split(',')}")
    private String[] filterUrls; //不校验的资源

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public void invoke(FilterInvocation fi) throws IOException, ServletException {
        String url = fi.getRequestUrl();
        if (!enableAuth) {//false时不进入权限校验
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } else {
            for (String f : filterUrls) {
                if (antPathMatcher.match(f, url)) {//不校验
                    fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
                    return;
                }
            }
            //校验权限
            InterceptorStatusToken token = super.beforeInvocation(fi);
            try {
                fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
            } finally {
                super.afterInvocation(token, null);
            }

        }
    }

    @Override
    public void destroy() {

    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
}
