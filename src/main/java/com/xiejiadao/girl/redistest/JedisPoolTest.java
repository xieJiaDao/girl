package com.xiejiadao.girl.redistest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: xyf
 * @date: 2019/7/20 11:59
 */
@Slf4j
public class JedisPoolTest {
    private static GenericObjectPoolConfig poolConfig = null;
    private static JedisPool jedisPool = null;

    static {
        poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMaxTotal(10);
        poolConfig.setMaxWaitMillis(50000);

        log.info("连接池中的默认参数，maxTotal:{}, maxIdle:{}, minIdle:{}, jmxEnabled:{}, ",
                poolConfig.getMaxTotal(), poolConfig.getMaxIdle(), poolConfig.getMinIdle(),
                poolConfig.getJmxEnabled());
        jedisPool = new JedisPool(poolConfig, "192.168.30.115", 6379);
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }

    public static void main(String[] args) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth("123456");
            log.info("set返回：{}", jedis.set("migu:str","谢亚非"));
            log.info("get返回：{}", jedis.get("migu:str"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
    }


}
