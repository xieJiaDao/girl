package com.xiejiadao.girl.publish.singleton;

import com.xiejiadao.girl.annotations.NotThreadSafe;

/**
 * 懒汉模式：第一次使用时候创建/
 * 线程不安全的
 * @author: xyf
 * @date: 2019/7/17 23:53
 */
@NotThreadSafe
public class SingletonExample1 {

    /**
     * 私有的构造函数
     */
    private SingletonExample1() {

    }

    //单例的对象
    private SingletonExample1 instance = null;

    /**
     * 静态的工厂方法来获取一个对象
     * 这个方法在单线程下没问题，但是多线程就会存在并发问题了，有可能实例化出来多个对象。
     * 比如多个线程执行该方法，同时判断instance是否为null的时候，都为null，那么就会同时执行new 实例。
     *
     * @return
     */
    public SingletonExample1 getInstance(){
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }


}
