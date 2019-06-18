package com.xinaml.common.redis;

import com.xinaml.common.constant.MsgConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author: [lgq]
 * @Date: [19-6-1 下午2:01]
 * @Description: redis工具类
 * @Version: [1.0.0]
 * @Copy: [com.xinaml]
 */
public class RedisRep implements ApplicationContextAware {
    private static Logger LOG = LoggerFactory.getLogger(RedisRep.class);
    @Autowired
    private StringRedisTemplate template;

    /**
     * 设置值
     *
     * @param key
     * @param value
     */
    public void put(String key, String value) {
        try {
            ValueOperations<String, String> ops = template.opsForValue();
            ops.set(key, value);
        } catch (RedisConnectionFailureException e) {
            LOG.error(MsgConst.REDIS_FAIL_MSG);
            throw new RuntimeException(e.getMessage());
        }


    }

    /**
     * 设置值
     *
     * @param key
     * @param value
     * @param timeOut
     * @param timeUnit
     */
    public void put(String key, String value, long timeOut, TimeUnit timeUnit) {
        try {
            ValueOperations<String, String> ops = template.opsForValue();
            ops.set(key, value, timeOut, timeUnit);
        } catch (RedisConnectionFailureException e) {
            LOG.error(MsgConst.REDIS_FAIL_MSG);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取值
     *
     * @param key
     * @return
     */
    public String get(String key) {
        try {
            ValueOperations<String, String> ops = this.template.opsForValue();
            return ops.get(key);
        } catch (RedisConnectionFailureException e) {
            LOG.error(MsgConst.REDIS_FAIL_MSG);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 设置过期
     *
     * @param key
     * @param timeOut
     * @param timeUnit
     */
    public void expire(String key, long timeOut, TimeUnit timeUnit) {
        try {
            this.template.expire(key, timeOut, timeUnit);
        } catch (RedisConnectionFailureException e) {
            LOG.error(MsgConst.REDIS_FAIL_MSG);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 获取过期时间
     *
     * @param key
     * @param timeUnit
     */
    public Long getExpire(String key, TimeUnit timeUnit) {
        try {
            return this.template.getExpire(key, timeUnit);
        } catch (RedisConnectionFailureException e) {
            LOG.error(MsgConst.REDIS_FAIL_MSG);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 删除建
     *
     * @param key
     */
    public void del(String key) {
        try {
            this.template.delete(key);
        } catch (RedisConnectionFailureException e) {
            LOG.error(MsgConst.REDIS_FAIL_MSG);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 是否存在
     *
     * @param key
     */
    public boolean exists(String key) {
        try {
            return this.template.hasKey(key);
        } catch (RedisConnectionFailureException e) {
            LOG.error(MsgConst.REDIS_FAIL_MSG);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 保存集合
     *
     * @param key
     * @param value
     */
    public void setHashSet(String key, String... value) {
        try {
            template.opsForSet().add(key, value);
        } catch (RedisConnectionFailureException e) {
            LOG.error(MsgConst.REDIS_FAIL_MSG);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 根据key获取集合
     *
     * @param key
     * @return
     */
    public Set<String> getHashSet(String key) {
        try {
            return template.opsForSet().members(key);
        } catch (RedisConnectionFailureException e) {
            LOG.error(MsgConst.REDIS_FAIL_MSG);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 根据key查看集合中是否存在指定数据
     *
     * @param key
     * @param value
     * @return
     */
    public boolean existsHashSet(String key, String value) {
        try {
            return template.opsForSet().isMember(key, value);
        } catch (RedisConnectionFailureException e) {
            LOG.error(MsgConst.REDIS_FAIL_MSG);
            throw new RuntimeException(e.getMessage());
        }
    }

    public static RedisRep build(){
        return getBean("redisConf",RedisRep.class);
    }

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext;
    }
    public static <T> T getBean(String name, Class<T> cls) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext注入失败!");
        }
        return applicationContext.getBean(name, cls);
    }
}
