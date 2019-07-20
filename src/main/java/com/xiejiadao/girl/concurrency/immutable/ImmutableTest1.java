package com.xiejiadao.girl.concurrency.immutable;

import com.google.common.collect.Maps;
import com.xiejiadao.girl.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author: xyf
 * @date: 2019/7/20 16:13
 */
@NotThreadSafe
@Slf4j
public class ImmutableTest1 {


    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 6);
    }


    public static void main(String[] args) {
        //final 修饰基础数据类型，一旦初始化就不能修改了。
        //a = 2; idea 提示 make a not final
        //b = "3"; idea 提示 make b not final
        //map = Maps.newHashMap(); idea 提示 make map not final
        map.put(1, 3);
        log.info("{}", map.get(1));
    }
}
