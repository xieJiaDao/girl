package com.xiejiadao.girl.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * CyclicBarrier await(time)
 */
@Slf4j
public class CyclicBarrierExample2 {

    /**
     * 设置屏障是5，当有5个线程执行awit()方法，等待后，就会继续执行
     */
    private final static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

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
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error("InterruptedException:", e);
        } catch (BrokenBarrierException e) {
            log.error("BrokenBarrierException:", e);
        } catch (TimeoutException e) {
            log.error("TimeoutException:", e);
        }
        log.info("{} continue execute", threadNum);
    }

}
