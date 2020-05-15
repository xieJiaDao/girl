package com.xiejiadao.girl.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author: xyf
 * @date: 2020/4/29 0:23
 */
@Slf4j
public class TestThreadLocal {
    static ThreadLocal<Person> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("" + tl.get()); //get不到下边线程set进去的Person对象；ThreadLocal天然的线程隔离。
        }).start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //这个set很迷惑，不看源码还以为是往threadLocal中set一个person；
            //其实set方法源码第一步是获取当前线程 Thread t = Thread.currentThread();
            //第二步是获取当前线程的 t.threadLocals;（定义在线程中的：Thread.java ThreadLocal.ThreadLocalMap threadLocals = null;）
            //第三步才是t.threadLocals.set(tl, new Person),所以别的线程拿不到这个person
            tl.set(new Person());
        }).start();
    }



    static class Person {
        String name = "zhangsan";
    }
}
