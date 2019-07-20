package com.xiejiadao.girl.concurrency.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * google-guava
 * Collections
 * @author: xyf
 * @date: 2019/7/20 17:09
 */
@Slf4j
public class ImmutableTest2 {
    private  static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
        map = Collections.unmodifiableMap(map);
//        map = Maps.newHashMap();
    }

    public static void main(String[] args) {
        log.info("throw");
        map.put(2, 4);
    }
}
