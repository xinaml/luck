package com.xinaml.user.config.auth;

import com.xinaml.user.ser.UserSer;
import com.xinaml.user.util.PwdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * 初始化默认的clientDetails
 */
@Component
public class StartInit {
    @Autowired
    private UserSer userSer;
    @Transactional
    @PostConstruct
    public void init(){
        String sql="select client_id from oauth_client_details where client_id='luck'";
        BaseClientDetails clientDetails = userSer.findOneBySql(sql,BaseClientDetails.class,"clientId");
        if(null==clientDetails){
            sql="insert into oauth_client_details" +
                    "(client_id,client_secret,authorized_grant_types,scope) " +
                    "values('luck','"+ PwdUtil.encode("123456") +"','authorization_code,password,refresh_token,client_credentials','luck')";
            userSer.executeSql(sql);
        }
    }
}
