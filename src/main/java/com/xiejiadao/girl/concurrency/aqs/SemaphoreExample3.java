package com.xiejiadao.girl.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore --并发过多，丢弃任务
 *
 */
@Slf4j
public class SemaphoreExample3 {
    private final static int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            cachedThreadPool.execute(() -> {
                try {
                    //尝试获取许可，如果获取失败就不获取，丢弃了
                    //也可以设置获取时间，在等待时间内获取到就还执行。
                    if (semaphore.tryAcquire()) {
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
