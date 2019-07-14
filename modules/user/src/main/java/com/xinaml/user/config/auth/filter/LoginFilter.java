package com.xinaml.user.config.auth.filter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录过滤器
 */
public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    // 登陆类型：为空默认账号密码登录，phone:手机验证码登陆；qr:二维码扫码登陆
    private static final String TYPE = "type";
    public static final String PHONE = "phone";
    private static final String VERIFY_CODE = "verifyCode";
    public static final String QR = "qr";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        String type = obtainParameter(request, TYPE);
        AbstractAuthenticationToken authRequest;
        String principal;
        String credentials;

        // 手机验证码登陆
        if (PHONE.equals(type)) {
            principal = obtainParameter(request, PHONE);
            credentials = obtainParameter(request, VERIFY_CODE);
            principal = principal.trim();
            authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
        }
        // 二维码扫码登陆
        else if (QR.equals(type)) {
            principal = obtainParameter(request, QR);
            credentials = null;
            authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
        }
        // 账号密码登陆
        else {
            principal = obtainParameter(request, USERNAME);
            credentials = obtainParameter(request, PASSWORD);
            principal = principal.trim();
            authRequest = new UsernamePasswordAuthenticationToken(principal, credentials);
        }
        setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private void setDetails(HttpServletRequest request,
                            AbstractAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String obtainParameter(HttpServletRequest request, String parameter) {
        String result = request.getParameter(parameter);
        return result == null ? "" : result;
    }
}
