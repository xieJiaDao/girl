package com.xiejiadao.girl.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * CountDownLatch --测试使用
 *
 * 指定任务的执行时间，完不成也不管了。
 *
 */
@Slf4j
public class CountDownLatchExample2 {
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
        countDownLatch.await(10, TimeUnit.MILLISECONDS );

        log.info("finish");
        //注意线程池不使用要shutdown
        cachedThreadPool.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
//        Thread.sleep(100);
        log.info("threadNum:{}",threadNum);
        Thread.sleep(1);
    }
}
