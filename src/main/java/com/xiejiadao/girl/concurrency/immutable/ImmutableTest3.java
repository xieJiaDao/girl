package com.xiejiadao.girl.concurrency.immutable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

/**
 * google-guava
 * Collections
 * @author: xyf
 * @date: 2019/7/20 17:09
 */
@Slf4j
public class ImmutableTest3 {

    private final static ImmutableList immutableList = ImmutableList.of(1, 2, 3);

    private final static ImmutableSet immutableSet = ImmutableSet.copyOf(immutableList);

    private final static ImmutableMap<Integer, String> immutableMap = ImmutableMap.of(1, "aa", 2, "bb");

    private final static ImmutableMap<Integer, Integer> immutableMap2 = ImmutableMap.<Integer, Integer>builder().put(1, 1).put(2, 2).build();

    public static void main(String[] args) {
//        immutableList.add(4);  --No
//        immutableSet.add(5);   --No

//        immutableMap.put

    }
}
