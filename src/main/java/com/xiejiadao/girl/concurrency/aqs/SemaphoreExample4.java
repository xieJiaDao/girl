package com.xiejiadao.girl.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore --并发过多设置获取时间，超出时间的丢弃
 *
 * tryAcquire(timeout, TimeUnit)
 *
 */
@Slf4j
public class SemaphoreExample4 {
    private final static int threadCount = 50;

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            cachedThreadPool.execute(() -> {
                try {
                    //也可以设置获取时间，在等待时间内获取到就还执行。
                    //threadPool里有50个线程在近乎同时尝试获取acquire，但是有些线程尝试的时间超过了5s就放弃了
                    if (semaphore.tryAcquire(5000, TimeUnit.MILLISECONDS)) {
                        test(threadNum);
                        semaphore.release();
                    }
                } catch (InterruptedException e) {
                    log.error("Thread Exception:", e);
                }
            });


        }
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("第【{}】个线程",threadNum);
        Thread.sleep(1000);
    }


}
