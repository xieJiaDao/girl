package com.xiejiadao.girl.StringTest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试String split 正则分割
 */
public class TestSplit {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = null;
        for (Integer integer : arrayList) {

        }
        String context = "#223\n" +
                "巍峨我热\n" +
                "#1231\n" +
                "#1234\n" +
                "撒旦#66我123\n" +
                "#111\n" +
                "撒旦";
        String pattern = "#\\d{1,}\n";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(context);
        List<String> matchStrs = new ArrayList<>();

        while (m.find()) { //此处find（）每次被调用后，会偏移到下一个匹配
            matchStrs.add(m.group());//获取当前匹配的值
        }

        System.out.println(matchStrs);
        for ( String str : matchStrs) {
//            System.out.println(str.indexOf('\n'));
            System.out.println(str.substring(1, str.indexOf('\n')));
        }
        String[] contextArray = context.split(pattern);
        for (String str:
                contextArray) {
            System.out.println("*****\n"+str);
        }
    }
}
