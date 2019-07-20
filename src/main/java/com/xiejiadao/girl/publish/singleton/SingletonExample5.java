package com.xiejiadao.girl.publish.singleton;

import com.xiejiadao.girl.annotations.ThreadSafe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用枚举来 实现单例
 *
 * 更能保证单例（因为可以被反射）
 * 相比于饿汉模式，它能等到被调用的时候才会创建这个单例
 *
 *
 * @author: xyf
 * @date: 2019/7/18 21:42
 */
@ThreadSafe
public class SingletonExample5 {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            threadPool.execute(() -> {
                System.out.println(SingletonExample5.getInstance());
                System.out.println("当前线程："+ Thread.currentThread().getName() + "线程id：" + Thread.currentThread().getId());
            });
        }
    }

    private SingletonExample5() {

    }

    public static SingletonExample5 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton{
        INSTANCE;
        private SingletonExample5 singletonExample5;
        //JVM保证了枚举的构造函数只能执行一次
        Singleton(){
            singletonExample5 = new SingletonExample5();
        }

        public SingletonExample5 getInstance() {
            return singletonExample5;
        }

    }



}
