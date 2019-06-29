package com.xinaml.common.utils;

import com.alibaba.fastjson.JSON;
import com.xinaml.common.exception.SerException;
import com.xinaml.common.redis.RedisRep;
import com.xinaml.common.vo.UserVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: [lgq]
 * @Date: [19-6-18 下午3:16]
 * @Description: 用户工具类
 * @Version: [1.0.0]
 * @Copy: [com.xianml]
 */
public class UserUtil {

    public static UserVO getUser(String token) {
        if(StringUtils.isNotBlank(token)){
            RedisRep redisRep = RedisRep.build();
            String json = redisRep.get(token);
            if (null != json) {
                return JSON.parseObject(json, UserVO.class);
            }
        }
        throw new SerException("非法请求！", HttpStatus.UNAUTHORIZED.value());
    }
    public static UserVO getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserVO u = (UserVO) authentication.getPrincipal();
        return u;
    }


}
