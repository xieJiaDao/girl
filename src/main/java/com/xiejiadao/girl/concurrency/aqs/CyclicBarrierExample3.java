package com.xiejiadao.girl.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * CyclicBarrier await(time)
 */
@Slf4j
public class CyclicBarrierExample3 {

    /**
     * 设置屏障是5，当有5个线程执行awit()方法，等待后，就会继续执行
     */
    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
        //当到达屏障后，优先执行这个指定的线程。
        log.info("call back is running");

    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            cachedThreadPool.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    log.error("Exception:",e);
                }
            });
        }

        cachedThreadPool.shutdown();

    }

    /**
     * 多个线程竞争
     */
    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        try {
            //超出两秒后就会执行下边的处理
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            log.error("InterruptedException:", e);
        } catch (BrokenBarrierException e) {
            log.error("BrokenBarrierException:", e);
        }
        log.info("{} continue execute", threadNum);
    }

}
