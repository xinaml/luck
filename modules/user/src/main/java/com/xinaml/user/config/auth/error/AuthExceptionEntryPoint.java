package com.xinaml.user.config.auth.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xinaml.common.result.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token异常
 *
 * @Author: [lgq]
 * @Date: [19-6-21 上午10:56]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Component
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException)
            throws ServletException {
        String msg ="无效的token!";
        if(authException.getMessage().startsWith("Full authentication")){
            msg="非法的请求！";
        }
        Result result = new Result(401, msg);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), result);
        } catch (Exception e) {
            throw new ServletException();
        }
    }

}
