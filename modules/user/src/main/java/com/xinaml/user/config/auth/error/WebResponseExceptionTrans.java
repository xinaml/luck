package com.xinaml.user.config.auth.error;

import com.xinaml.common.result.Result;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.stereotype.Component;

/**
 * @Author: [lgq]
 * @Date: [19-6-21 下午2:00]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Component
public class WebResponseExceptionTrans extends DefaultWebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        e.printStackTrace();
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

}
