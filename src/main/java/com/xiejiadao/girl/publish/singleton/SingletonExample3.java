package com.xiejiadao.girl.publish.singleton;

import com.xiejiadao.girl.annotations.ThreadSafe;

/**
 * 懒汉模式：线程安全的 第一次使用时候创建/
 * @author: xyf
 * @date: 2019/7/17 23:53
 */
@ThreadSafe
public class SingletonExample3 {

    /**
     * 私有的构造函数
     */
    private SingletonExample3() {

    }

    //单例的对象
    private SingletonExample3 instance = null;

    /**
     *虽然保证了并发问题，但是性能开销较大。
     * @return
     */
    public synchronized SingletonExample3 getInstance(){
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }


}
