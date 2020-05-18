package com.xiejiadao.girl.concurrency.synchronizedtest;

import com.xiejiadao.girl.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * synchronized : 不可中断锁，适合竞争不激烈，可读性好
 * Lock ： 可中断锁，多样化同步，竞争激烈时能维持常态
 * Atomic : 竞争激烈的时候能维持常态，比Lock性能好； 只能同步一个值
 *
 * @author: xyf
 * @date: 2020/5/18 22:19
 */
@ThreadSafe
@Slf4j
public class SynchronizedTest {
    public void test1() {
        //同步代码块 - 作用范围是修饰的代码块
        synchronized(this){
            for (int i = 0; i < 10 ; i++) {
                log.info("current thread: {}, test1 - {}",Thread.currentThread().getName(), i);
            }
        }
    }

    //同步方法 - 作用范围是test2
    // 如果子类继承当前类，调用test2方法，不会由synchronized效果，因为synchronized它不输入方法声明中的一部分。
    public synchronized void test2() {

        for (int i = 0; i < 10 ; i++) {
            log.info("current thread: {}, test2 - {}",Thread.currentThread().getName(), i);
        }
    }


    public static void main(String[] args) {
        SynchronizedTest example1 = new SynchronizedTest();
        SynchronizedTest example2 = new SynchronizedTest();
        new Thread(() -> {
            example1.test1();
        }, "test1-Thread1").start();
        new Thread(() -> {
            example1.test1();
        }, "test1-Thread2").start();
        new Thread(() -> {
            example1.test1();
        }, "test1-Thread3").start();
        new Thread(() -> {
            example2.test1();
        }, "test1-Thread4").start();
        new Thread(() -> {
            example2.test1();
        }, "test1-Thread5").start();
        new Thread(() -> {
            example2.test1();
        }, "test1-Thread6").start();

//        ExecutorService executorService = Executors.newCachedThreadPool();
//        executorService.execute(() ->{
//            example1.test2();
//        });
//        executorService.execute(() ->{
//            example1.test2();
//        });
//        executorService.shutdown();
    }



}
