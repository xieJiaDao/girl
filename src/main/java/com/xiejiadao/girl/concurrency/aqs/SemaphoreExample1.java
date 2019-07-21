package com.xiejiadao.girl.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Semaphore --测试单个许可
 * 控制并发量。
 *
 * 数据库的连接数最大只有20；
 *
 * semaphore如果控制个数为1，就相当于单线程执行了、
 *
 */
@Slf4j
public class SemaphoreExample1 {
    private final static int threadCount = 20;

    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            cachedThreadPool.execute(() -> {
                try {
                    //执行的时候获取一个许可。如果此时并发已经是20了，我们就得等待释放了
                    semaphore.acquire();
                    test(threadNum);
                    // 执行完了要释放一个许可
                    semaphore.release();
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
