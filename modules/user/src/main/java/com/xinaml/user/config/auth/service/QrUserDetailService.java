package com.xinaml.user.config.auth.service;

import com.xinaml.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: [lgq]
 * @Date: [19-5-13 上午10:01]
 * @Description:
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
@Service
public class QrUserDetailService extends BaseUserDetailService {
    @Override
    protected User getUser(String qrCode) {
        return null;
    }
}
