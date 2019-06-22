package com.xinaml.common.interceptor;

import com.xinaml.common.constant.CommonConst;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: [lgq]
 * @Date: [19-5-31 下午4:12]
 * @Description: request header传递
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class FeginInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        try {
            Map<String, String> headers = getHeaders();
            for (String headerName : headers.keySet()) {
                requestTemplate.header(headerName, headers.get(headerName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> getHeaders() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String key = enumeration.nextElement();
            String value = request.getHeader(key);
            if(key.equals(CommonConst.TOKEN)){ //header传递的是access_token，更改为oatuh2接受的Authorization：Bearer token
                key="Authorization";
                if(StringUtils.isBlank(value)){
                    value="Bearer"+request.getParameter(CommonConst.TOKEN);
                }else {
                    value="Bearer"+value;
                }
            }
            map.put(key, value);
        }
        return map;
    }
}
