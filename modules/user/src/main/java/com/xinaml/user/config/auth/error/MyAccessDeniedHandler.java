package com.xinaml.user.config.auth.error;

import com.xinaml.common.result.Result;
import com.xinaml.common.utils.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: [lgq]
 * @Date: [19-6-24 上午10:47]
 * @Description: 权限异常处理器
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResponseUtil.writeData(new Result(403,"没有访问权限！"));
    }
}
