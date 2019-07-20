package com.xiejiadao.girl.redistest;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * 【Jedis直连操作】
 * 测试redis的基本操作
 * 五大数据类型：
 * string hash list set zset(sorted set)
 * @author: xyf
 * @date: 2019/7/20 10:18
 */
@Slf4j
public class JedisTest {
    private static Jedis jedis;
    static {
        //本质是tcp连接 ，socket
        jedis = new Jedis("192.168.30.115");
        jedis.auth("123456");
    }
    public static void main(String[] args) {
//        testStringKey();
//        testHashKey();
//        testListKey();
//        testSetKey();
        testZsetKey();
        //用完别忘了关闭连接。
        jedis.close();
    }

    private static void testStringKey() {
        //        log.info("append操作返回：{}",jedis.append("posts:count","1108"));
        log.info("set操作返回值：{}",jedis.set("亚龙","niubility"));
        log.info("set操作返回值：{}",jedis.get("亚龙"));
        log.info("set操作返回值：{}",jedis.del("亚龙"));
    }

    private static void testHashKey() {
        log.info("hset操作返回值：{}",jedis.hset("hash:migu","mdg","eb"));
        log.info("hgetall操作返回值：{}",jedis.hgetAll("hash:migu"));
        log.info("hdel操作返回值：{}",jedis.hdel("hash:migu","mdg"));
        log.info("hgetall操作返回值：{}",jedis.hgetAll("hash:migu"));
        log.info("删除hash的key也是del操作，返回值：{}",jedis.del("hash:migu"));
        log.info("hgetall操作返回值：{}",jedis.hgetAll("hash:migu"));
    }

    private static void testListKey() {
//        log.info("lpush的返回值:{}",jedis.lpush("migu:list1","xyf","zhl"));
        log.info("lpush的返回值:{}",jedis.lpush("migu:list2","xyf","zhl"));
        log.info("lpush的返回值:{}",jedis.lpush("migu:list3","xyf","zhl"));
        log.info("lrange的返回值：{}",jedis.lrange("migu:list1",0l,-1l));
        log.info("lpop的返回值：{}", jedis.lpop("migu:list1"));
        log.info("lrange的返回值：{}",jedis.lrange("migu:list1",0l,-1l));
        log.info("blpop的返回值：{}",jedis.blpop(5,"migu:list1"));
        log.info("lrange的返回值：{}",jedis.lrange("migu:list1",0l,-1l));
    }

    private static void testSetKey() {
        log.info("sadd操作的返回值：{}",jedis.sadd("migu:set1","xyf","zlf"));
        log.info("smembers操作的返回值：{}", jedis.smembers("migu:set1"));
    }

    private static void testZsetKey() {
        log.info("zadd操作返回值：{}", jedis.zadd("migu:zset1",99d,"xieyafei"));
        log.info("zadd操作返回值：{}", jedis.zadd("migu:zset1",90d,"zhaolingfei"));
        log.info("zadd操作返回值：{}", jedis.zadd("migu:zset1",60d,"zhangchong"));
        log.info("zadd操作返回值：{}", jedis.zadd("migu:zset1",88d,"zhangyan"));
        log.info("zrangeWithScores操作返回值：{}",jedis.zrangeWithScores("migu:zset1",0l,-1l));
        log.info("zrange操作返回值：{}",jedis.zrange("migu:zset1",0l,-1l));
    }
}
