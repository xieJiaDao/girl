package com.xiejiadao.girl.redistest;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

/**
 * 测试pipeline的性能：
 * 1. 注意每次pipeline携带数据量
 * 2. pipeline每次只能作用于一个redis节点上
 * 3. m（mget，mset）操作是原子操作，而pipeline是会把我们的命令拆分和别的客户端发的命令一起（夹杂）排队执行。
 *
 */
@Slf4j
public class PipelineTest {

    private static JedisPool jedisPool = JedisPoolTest.getJedisPool();

    public static void main(String[] args) {
        long starTime = System.currentTimeMillis();
//        testNoPipeline();
        testPipeline();
        long endTime = System.currentTimeMillis();
        log.info("方法执行耗时：{}", endTime - starTime);

    }

    /**
     * 不使用pipeline 耗时22469ms 22.469s；
     * 主要原因是需要n次网络开销，和n次执行命令
     */
    private static void testNoPipeline() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.auth("123456");
            for (int i = 0; i < 10000; i++) {
                jedis.hset("hashkey:" + i, "field" + i, "value" + i);
            }
        } catch (Exception e) {
            log.info("jedis异常：", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 使用pipeline 执行耗时：627ms 0.627s --
     * 主要原因是只需要100次网络开销和n次执行命令
     */
    private static void testPipeline() {
        Jedis jedis = null;
        Pipeline pipeline = null;

        try {
            jedis = jedisPool.getResource();

            for (int i = 0; i < 100; i++) {
                pipeline = jedis.pipelined();
                for (int j = i*100; j < (i+1)*100; j++) {
                    pipeline.hset("hashkey:" + i, "field" + i, "value" + i);
                }
                pipeline.syncAndReturnAll();
            }

        } catch (Exception e) {
            log.error("pipeline执行批量命令出现异常：", e);
        } finally {
            if (jedis != null) {
                jedis.close();
                pipeline.syncAndReturnAll();
            }
        }

    }


}
