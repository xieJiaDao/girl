package com.xiejiadao.girl.concurrency.aqs.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * juc 中锁的核心 ReentrantLock  --Condition
 *
 * ReentrantLock 与 Synchronized 的区别：
 *
 * 1. 两者都是可重入性质。
 *
 * 2.Synchronized 是依赖于JVM实现的，而RenentrantLock是JDK实现的。
 *
 *3. 之前Synchronized性能不太好，比ReentrantLock差很多。
 * 但是之后引入了偏向锁-自旋锁之后，两者性能差不多了。官方推荐 Synchronized
 *
 * 4.Synchronized使用更加简单，并且它的加锁和释放是编译器来保证的
 *  而ReentrantLock是需要手工加锁和释放锁的，为避免死锁，在finally里释放锁。
 *  RenentrantLock锁的细粒度更加优于Synchronized。
 *
 *  RenentrantLock 的独有功能：
 *  （1）它可以指定是公平锁，还是非公平锁。Synchronized 只能是非公平锁，先等待的线程，先获取锁。
 *  （2）它有Condition类，它实现了分组唤醒需要唤醒的线程。
 *  （3）它提供了能够中断 等待锁的线程 的机制。lock.lockInterruptibly();
 *  （4）它是一种自旋锁，通过循环调用cas来实现加锁。它性能好就是因为，cas避免了使线程进入内核态的阻塞状态。
 *
 */
@Slf4j
public class ReentrantLockExample {

    private static int clientTotal = 50;

    private static int threadTotal = 20;

    private static int count = 0;

    private final static Lock reentrantLock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);

        final Semaphore semphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int threadNum = i;
            cachedThreadPool.execute(() -> {
                try {
                    semphore.acquire();
                    add();
                    log.info("第{}个线程", threadNum);
                    semphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //别忘了，-1啊，不然不到0，awit的线程就死锁了。
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        log.info("最后count结果：{}", count);
        cachedThreadPool.shutdown();
    }

    private static void add() throws InterruptedException {
        reentrantLock.lock();
        try {
            Thread.sleep(1000);//在加锁后 sleep，和 加锁前 sleep 效果大不相同。
            count++;
        } finally {
            reentrantLock.unlock();
        }
    }

}
