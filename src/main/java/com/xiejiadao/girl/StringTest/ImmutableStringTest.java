package com.xiejiadao.girl.StringTest;

/**
 * 在Java中，当对象作为参数传递时，实际上传递的是一份“引用的拷贝”。
 *
 * 所以我们看String 作为参数传的也是引用的拷贝，当 s += "bbb" 的时候，实际是这个引用的拷贝又指向新创建的一个String实例了（因为String是不可变的，所以它是重新创建）
 *
 */
public class ImmutableStringTest {
    public static String appendStr(String s) {
        s += "bbb";
        return s;
    }

    public static String appendStr(StringBuffer s) {
        s.append("bbb");
        return s.toString();
    }

    public static void main(String[] args) {
        String s = "aaa";
        String s1 = ImmutableStringTest.appendStr(s);
        System.out.println("s:" + s); //s:aaa
        System.out.println("s1:" + s1); // s1:aaabbb

        StringBuffer sb = new StringBuffer("aaa");
        String s2 = ImmutableStringTest.appendStr(sb);
        System.out.println(sb.toString());
        System.out.println(s2);

        String str = "aaa";
        str += "bbb";
        System.out.println(str);
    }
}
