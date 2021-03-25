package com.xiejiadao.girl.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 【Least Recently Used】 :
 *  也就是最近最少使用的意思，是一种内存管理算法，最早应用于Linux系统。
 *
 * 根据jdk已有数据结构 实现一个lru算法
 *
 * @param <K>
 * @param <V>
 */
@Slf4j
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private static int cacheSize;

    public LRUCache(int cacheSize) {
        super((int)Math.ceil(cacheSize/0.75) + 1, 0.75f, true);
        LRUCache.cacheSize = cacheSize;
    }

    /**
     * LinkedHashMap 在新增一个元素后，会判断是否要删除最老的那个元素。。如果要，那就把最老的删除了。
     * @param eldest 最久没有被访问的键值对
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return cacheSize < size();
    }


    /**
     * 测试LRUCache
     * @param args
     */
    public static void main(String[] args) {
        LRUCache<String, Integer> lruCache = new LRUCache<>(3);
        lruCache.put("a", 1);
        lruCache.put("b", 2);
        lruCache.put("c", 3);
        lruCache.put("b", 4);

        log.info("LRUCache中的元素按照访问顺序：{}", lruCache.toString());
        Map map = new HashMap();
    }


}
