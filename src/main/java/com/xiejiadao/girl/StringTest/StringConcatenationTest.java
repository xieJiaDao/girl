package com.xiejiadao.girl.StringTest;

/**
 * 测试String 拼接的时候，
 * 哪种情况下会走会走StringBulider进行字符串拼接，而哪种情况编译器会对代码进行优化？
 */
public class StringConcatenationTest {
    public static void main(String[] args) {
        String a = "111";
        String c = "111" + "";
        System.out.println(a == c);//true
    }
}
