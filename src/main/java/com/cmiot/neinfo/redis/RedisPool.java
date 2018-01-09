package com.cmiot.neinfo.redis;

import com.cmiot.neinfo.utils.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description:
 * date 2018/1/8
 *
 * @author lining1
 * @version 1.0.0
 */
public class RedisPool {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisPool.class);
    private static final ReentrantLock jedisLock = new ReentrantLock();
    private static final Map<String, String> redisConfigMap = SecurityProperties.getInstance().getPropertiesMap();
    private static JedisPool jedisPool;

    private static void createRedisPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        String host = "";
        if(StringUtils.isNotBlank(redisConfigMap.get("redis.host"))){
            host = redisConfigMap.get("redis.host");
        }
        Integer port = null;
        if(StringUtils.isNotBlank(redisConfigMap.get("redis.port"))){
            port = Integer.parseInt(redisConfigMap.get("redis.port"));
        }
        String password = redisConfigMap.get("redis.password");
        if(StringUtils.isNotBlank(redisConfigMap.get("redis.maxIdle"))){
            poolConfig.setMaxIdle(Integer.parseInt(redisConfigMap.get("redis.maxIdle")));
        }
        if(StringUtils.isNotBlank(redisConfigMap.get("redis.maxActive"))){
            poolConfig.setMaxTotal(Integer.parseInt(redisConfigMap.get("redis.maxActive")));
        }
        if(StringUtils.isNotBlank(redisConfigMap.get("redis.maxWait"))){
            poolConfig.setMaxWaitMillis(Long.parseLong(redisConfigMap.get("redis.maxWait")));
        }
        if(StringUtils.isNotBlank(redisConfigMap.get("redis.testOnBorrow"))){
            poolConfig.setTestOnBorrow(Boolean.parseBoolean(redisConfigMap.get("redis.testOnBorrow")));
        }
        if(StringUtils.isNotBlank(redisConfigMap.get("redis.testOnReturn"))){
            poolConfig.setTestOnReturn(Boolean.parseBoolean(redisConfigMap.get("redis.testOnReturn")));
        }
        Integer db = null;
        if(StringUtils.isNotBlank(redisConfigMap.get("redis.db"))){
            db = Integer.parseInt(redisConfigMap.get("redis.db"));
        }
        LOGGER.debug("获取Redis数据库index成功:{}", db);
        int timeout = 4000;
        if(StringUtils.isNotBlank(redisConfigMap.get("redis.timeout"))){
            timeout = Integer.parseInt(redisConfigMap.get("redis.timeout"));
        }
        LOGGER.debug("构造Redis连接池,host:{}, port:{}, db:{}", host, port, db);
        try{
            if(StringUtils.isNotBlank(password)){
                jedisPool = new JedisPool(poolConfig, host, port, timeout, password, db);
            } else {
                jedisPool = new JedisPool(poolConfig, host, port, timeout, null, db);
            }
        } catch (Exception e) {
            LOGGER.warn("构造Redis连接池失败:{}", e.getMessage());
        }
    }

    public static Jedis getJedis(){
        jedisLock.lock();
        Jedis jedis = null;
        try {
            if(null == jedisPool){
                createRedisPool();
            }
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            LOGGER.warn("获取Redis连接失败");
        } finally {
            jedisLock.unlock();
        }
        return jedis;
    }
}
