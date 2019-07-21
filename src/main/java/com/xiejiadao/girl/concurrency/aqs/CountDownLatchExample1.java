package com.xiejiadao.girl.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch --测试使用
 *
 * 【通过测试我们发现CountDownLatch 和 thread.join方法有点儿类似】：
 * （join） 首先来看一下join，在当前线程中，如果调用某个thread的join方法，
 * 那么当前线程就会被阻塞，直到thread线程执行完毕，当前线程才能继续执行。
 * join的原理是，不断的检查thread是否存活，如果存活，那么让当前线程一直wait，
 * 直到thread线程终止，线程的this.notifyAll 就会被调用。
 *
 * （CountDownLatch） CountDownLatch中我们主要用到两个方法一个是await()方法，
 * 调用这个方法的线程会被阻塞，另外一个是countDown()方法，调用这个方法会使计数器减一，
 * 当计数器的值为0时，因调用await()方法被阻塞的线程会被唤醒，继续执行。
 *
 * 所以对比下来看，我们发现CountDownLatch阻塞的线程不需要别的线程都执行完，而是只有计数器的值为0就可以唤醒
 * 假设A，B，C的工作都分为两个阶段，A只需要等待B，C各自完成他们工作的第一个阶段就可以执行了。
 * 这个时候join方法就不太好用了。用CountDownLatch 在第一阶段执行完之后就执行countDown()；
 *
 *
 */
@Slf4j
public class CountDownLatchExample1 {
    private final static int threadCount = 200;
    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            cachedThreadPool.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("slee异常：", e);
                } finally {
                    countDownLatch.countDown();
                }
            });

        }
        countDownLatch.await();

        log.info("finish");
        //注意线程池不使用要shutdown
        cachedThreadPool.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("threadNum:{}",threadNum);
        Thread.sleep(100);
    }
}
