package com.xiejiadao.girl.redistest;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: xyf
 * @date: 2019/7/20 14:39
 */
@Slf4j
public class JedisPoolOptimizeTest {
    public static void main(String[] args) {
//        NotRecommendJedisPoolUsage();
        RecommendJedisPoolUsage();
    }

    /**
     * 推荐JedisPool用法
     * 好借好还，再借不难。
     * 在finally 先判断jedis不为null,再close归还。
     */
    private static void RecommendJedisPoolUsage() {
        JedisPool jedisPool = null;
        Jedis jedis = null;
        for (int i = 0; i < 10; i++) {
            try {
                jedisPool = JedisPoolTest.getJedisPool();
                jedis = jedisPool.getResource();
                jedis.auth("123456");
                log.info("ping返回：{}, 第【{}】次", jedis.ping(), i);
            } catch (Exception e) {
                log.error("Jedis异常：", e);
            } finally {
                if (jedis != null) {
                    jedis.close();
                }
            }
        }
        Jedis jedis1 = jedisPool.getResource();
        jedis1.auth("123456");
        log.info("ping返回：{}", jedis1.ping());
    }

    /**
     * 不推荐的JedisPool的用法，因为只借不还，所以会导致exhausted,以致于后续没法借了，就会超过maxWaitMillis。
     * 抛出异常：JedisExhaustedPoolException: Could not get a resource since the pool is exhausted
     */
    private static void NotRecommendJedisPoolUsage() {
        JedisPool jedisPool = null;
        for (int i = 0; i < 10; i++) {
            try {
                jedisPool = JedisPoolTest.getJedisPool();
                Jedis jedis = jedisPool.getResource();
                jedis.auth("123456");
                log.info("ping返回：{}", jedis.ping());
            } catch (Exception e) {
                log.error("jedis异常：",e);
            }
        }
        //注意上边连接并没有归还，所以我再获取个连接
        //redis.clients.jedis.exceptions.JedisExhaustedPoolException: Could not get a resource since the pool is exhausted
        Jedis jedis1 = jedisPool.getResource();
        jedis1.auth("123456");
        jedis1.ping();
    }
}
