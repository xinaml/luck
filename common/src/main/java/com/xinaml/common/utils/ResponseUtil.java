package com.xinaml.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.xinaml.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Author: [lgq]
 * @Date: [19-6-1 下午2:01]
 * @Description: 写入数据到页面
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public final class ResponseUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtil.class);

    private ResponseUtil() {
    }

    public static HttpServletResponse get() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    private static HttpServletResponse init() {
        HttpServletResponse response = get();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        return response;
    }


    public static void writeData(Object data) {
        try {
            HttpServletResponse response = init();
            if(data instanceof Result){ //强制返回给前端json
                response.getWriter().print(JSON.toJSONString(data,SerializerFeature.WriteMapNullValue));
            }else {
                response.getWriter().print(JSON.toJSONString(new Result(data),SerializerFeature.WriteMapNullValue));
            }
            response.getWriter().close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }


}
