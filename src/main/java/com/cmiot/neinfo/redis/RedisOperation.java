package com.cmiot.neinfo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Map;

/**
 * description:
 * date 2018/1/8
 *
 * @author lining1
 * @version 1.0.0
 */
public class RedisOperation {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisOperation.class);

    public String getString(final String key) {
        Jedis jedis = null;
        String value = null;
        try {
            jedis = RedisPool.getJedis();
            value = jedis.get(key);
        } catch (Exception e) {
            LOGGER.warn("获取键:{}失败, 异常:{}", key, e);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        LOGGER.debug("获取key:{}对应的value为:{}",key, value);
        return value;
    }

    public String setString(final String key, final String value) {
        LOGGER.debug("设置键值对key:{}, value:{}", key, value);
        Jedis jedis = null;
        String retString = null;
        try {
            jedis = RedisPool.getJedis();
            retString = jedis.set(key, value);
        } catch (Exception e) {
            LOGGER.warn("设置key:{}对应的值value:{}失败, 异常:{}", key, value, e);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        return retString;
    }

    public Long setHash(final String key, final String field, final String value){
        LOGGER.debug("设置Hash,key:{}, field:{}, value:{}", key, field, value);
        Jedis jedis = null;
        Long result = null;
        try {
            jedis = RedisPool.getJedis();
            result = jedis.hset(key, field, value);
        } catch (Exception e) {
            LOGGER.warn("设置key:{}, field:{}, value:{} 失败, 异常:{}", key, field, value, e);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    public String getHash(final String key, final String field){
        LOGGER.debug("获取Hash,key:{}, field:{}", key, field);
        Jedis jedis = null;
        String value = null;
        try {
            jedis = RedisPool.getJedis();
            value = jedis.hget(key, field);
            jedis.hgetAll(key);
        } catch (Exception e) {
            LOGGER.warn("获取key:{}, field:{} 对应的值失败, 异常:{}", key, field, e);
        } finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        LOGGER.debug("获取key:{}, field:{} ,结果为:{}",key, field, value);
        return value;
    }

    public Map<String, String> getHashAll(final String key){
        LOGGER.debug("获取Hash全部信息,key:{}", key);
        Jedis jedis = null;
        Map<String, String> map = null;
        try {
            jedis = RedisPool.getJedis();
            map = jedis.hgetAll(key);
        } catch (Exception e) {
            LOGGER.warn("获取key:{}出错, 异常:{}", key, e);
        } finally {
            if(null != jedis){
                jedis.close();
            }
        }
        return map;
    }

    public Long expire(String key, int seconds) {
        LOGGER.debug("为键key:{} 设置有效期(时间为秒):{}", key, seconds);
        Jedis jedis = null;
        Long ret = null;
        try {
            jedis = RedisPool.getJedis();
            ret = jedis.expire(key, seconds);
        } catch (Exception e) {
            LOGGER.warn("设置键key:{} 有效期失败, 异常:{}", key, e);
        }
        finally {
            if(jedis != null) {
                jedis.close();
            }
        }
        LOGGER.debug("设置有效期结果为:{}", ret);
        return ret;
    }
}
