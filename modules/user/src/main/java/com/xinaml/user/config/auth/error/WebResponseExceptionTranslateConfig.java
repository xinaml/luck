package com.xinaml.user.config.auth.error;

import com.xinaml.common.result.Result;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

/**
 * @Description: web全局异常返回处理器
 * @author: zhoum
 * @Date: 2018-11-22
 * @Time: 14:48
 */
@Configuration
public class WebResponseExceptionTranslateConfig {
    /**
     * 自定义登录或者鉴权失败时的返回信息
     */
    @Bean(name = "webResponseExceptionTranslator")
    public WebResponseExceptionTranslator webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @Override
            public ResponseEntity translate(Exception e) throws Exception {
                ResponseEntity responseEntity = super.translate(e);
                OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                String summary = body.getSummary();
                String msg = body.getMessage();
                if ("Bad credentials".equals(msg) || summary.indexOf("unauthorized") != -1) {
                    return new ResponseEntity(new Result(401, "账号或密码错误！", null), headers, HttpStatus.OK);
                }
                return new ResponseEntity(body, headers, responseEntity.getStatusCode());

            }
        };
    }
}
