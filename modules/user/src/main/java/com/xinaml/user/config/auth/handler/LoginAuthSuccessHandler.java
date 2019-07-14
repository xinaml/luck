package com.xinaml.user.config.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 登录成功处理
 */
@Component
public class LoginAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String type = request.getHeader("Accept");
        if (!type.contains("text/html")) {
            String clientId = "luck";
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            String grantType = StringUtils.join(clientDetails.getAuthorizedGrantTypes().toArray(),",");
            TokenRequest tokenRequest = new TokenRequest(Maps.newHashMap(), clientId, clientDetails.getScope(),grantType );
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(token));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }

    }
}