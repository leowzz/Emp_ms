package com.leo.utils;

import redis.clients.jedis.*;

import java.util.Map;

/**
 * @author Leo Wang
 * @version 1.0.0
 * @description Redis操作类
 * @date 2023/5/22 13:57
 **/
public class RedisUtils {
    public static JedisPool pool = null;
    
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);
        config.setMaxIdle(10);
        config.setTestOnBorrow(true);
        try {
            pool = new JedisPool(config, "localhost", 6379);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Jedis getJedis() {
        return pool.getResource();
    }
    
    public static void set(String key, String value) {
        Jedis jedis = getJedis();
        jedis.set(key, value);
        jedis.close();
    }
    
    public static String get(String key) {
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        jedis.close();
        return value;
    }
    
    public static void hset(String key, String field, String value) {
        Jedis jedis = getJedis();
        jedis.hset(key, field, value);
        jedis.close();
    }
    
    public static String hget(String key, String field) {
        Jedis jedis = getJedis();
        String value = jedis.hget(key, field);
        jedis.close();
        return value;
    }
    
    public static void del(String key) {
        Jedis jedis = getJedis();
        jedis.del(key);
        jedis.close();
    }
    
    public static void hdel(String key, String field) {
        Jedis jedis = getJedis();
        jedis.hdel(key, field);
        jedis.close();
    }
    
    public static void setex(String key, int seconds, String value) {
        Jedis jedis = getJedis();
        jedis.setex(key, seconds, value);
        jedis.close();
    }
    
    public static void expire(String key, int seconds) {
        Jedis jedis = getJedis();
        jedis.expire(key, seconds);
        jedis.close();
    }
    
    public static boolean exists(String key) {
        Jedis jedis = getJedis();
        boolean exists = jedis.exists(key);
        jedis.close();
        return exists;
    }
    
    
}
