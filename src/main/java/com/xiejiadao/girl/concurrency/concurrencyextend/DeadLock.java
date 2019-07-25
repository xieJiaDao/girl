package com.xiejiadao.girl.concurrency.concurrencyextend;

import lombok.extern.slf4j.Slf4j;

/**
 * 死锁的产生：
 *  必要条件：
 *  （1）互斥条件
 *  （2）请求和保持
 *  （3）不剥夺条件：只能自己释放资源
 *  （4）环路等待条件：资源环形链
 *
 *  死锁检测：
 *
 */
@Slf4j
public class DeadLock implements Runnable {

    private int flag = 1;

    private static Object o1 = new Object(), o2 = new Object();

    /**
     * o1 和 o2 同时只能被一个线程获取（1）
     * synchronized 关键字是有jvm控制的，所以资源开发人员无法释放。（3）
     * 我们可以看到d1持有o2的同时去获取o1，d2持有o1的同时去获取o2.这样就形成了资源环形链（2）（4）
     */
    @Override
    public void run() {

        log.info("flag:{}", flag);
        if (flag == 1) {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error("Exception:", e);
                    synchronized (o2) {
                        log.info("获取到o2");
                    }
                }
            }
        }
        if (flag == 0) {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    log.error("Exception:", e);
                }
                synchronized (o1) {
                    log.info("获取到o1");
                }
            }
        }
    }

    public static void main(String[] args) {
        DeadLock d1 = new DeadLock();
        d1.flag = 0;
        DeadLock d2 = new DeadLock();
        d2.flag = 1;

        log.info("下面开始死锁的表演！！");
        new Thread(d1).start();
        new Thread(d2).start();
    }
}
