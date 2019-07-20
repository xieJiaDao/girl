package com.xiejiadao.girl.publish.singleton;

/**
 * 饿汉模式：类在装载时就创建。
 *
 * 使用时机：私有构造方法中没有过多的处理  确定会被使用
 *
 * @author: xyf
 * @date: 2019/7/18 0:00
 */
public class SingletonExample2 {
    private SingletonExample2() {

    }

    private static SingletonExample2 instance = new SingletonExample2();

    public static SingletonExample2 getInstance() {
        return instance;
    }
}
