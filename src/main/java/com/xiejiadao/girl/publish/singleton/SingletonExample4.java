package com.xiejiadao.girl.publish.singleton;

import com.xiejiadao.girl.annotations.ThreadSafe;

/**
 * 懒汉模式：双重同步锁单例模式
 * @author: xyf
 * @date: 2019/7/17 23:53
 */
@ThreadSafe
public class SingletonExample4 {

    /**
     * 私有的构造函数
     */
    private SingletonExample4() {

    }

    //单例的对象
    private volatile SingletonExample4 instance = null;

    /**
     * new操作分步骤解析：
     * 1. memory = allocate() //分配对象的内存空间
     * 2.ctorInstance //初始化对象
     * 3.instance=memory; //设置instance指向刚分配的内存地址
     *
     * 2 3 两步有可能被指令重排序，所以就导致一个线程先执行了第三步。
     *  而同时另一个线程获取到了这个实际上还没初始化完成的对象。
     *
     *  基于volatile的解决方案，不允许2和3重排序
     *
     * @return
     */
    public SingletonExample4 getInstance(){
        if (instance == null) {
            synchronized (SingletonExample4.class) {
                if (instance == null) {
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }


}
