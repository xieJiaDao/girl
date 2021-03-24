package com.xiejiadao.girl;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.HashMap;

/**
 * @author: xyf
 * @date: 2020/2/29 15:10
 */
public class Test {
    public static void main(String[] args) {
        String a = "1.2./3";
        String b = "./3";
        System.out.println(a.contains(b));

        testRegular();
    }

    public static void testRegular() {
        String str = "/\\\\\"/\\";
        System.out.println(str);
        String jsonStr = StringEscapeUtils.escapeJson(str);
        System.out.println("jsonStr" + jsonStr);
        String decompilingJsonStr = StringEscapeUtils.unescapeJson(str);
        System.out.println(decompilingJsonStr);

    }

    public static void testHashMapComputerIfAbsent() {
        HashMap<String, Integer> map
                = new HashMap<>();
//        map.put("key1", 10000);
        map.put("key2", 20000);
        map.put("key3", 30000);
        map.put("key4", 40000);

        // print main.java.com.test.map details
        System.out.println("HashMap:\n "
                + map.toString());

        // provide value for new key which is absent
        // using computeIfAbsent method
        map.computeIfAbsent("key5",
                k -> 20000 + 30000);
        map.computeIfAbsent("key6",
                k -> 20000 * 3);

        //if already exists
        Integer integer = map.computeIfAbsent("key1", k -> 9999999);
        System.out.println("integer:" + integer);

        // print new mapping
        System.out.println("New HashMap:\n "
                + map);
    }
}
