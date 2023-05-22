package com.leo.utils;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

/**
 * @author Leo Wang
 * @version 1.0.0
 * @description
 * @date 2023/5/22 14:08
 **/
public class RedisUtilsTest {
    
    @Test
    public void set() {
        RedisUtils.set("name", "leo");
        System.out.println(RedisUtils.get("name"));
    }
}