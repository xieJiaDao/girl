package com.xiejiadao.girl.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier 和 CountDownLatch比较相似
 *
 * 区别：（1）CountDownLatch的计数器只能用一次，而CyclicBarrier的计数器能循环使用
 * 
 * （2）CountDownLatch 主要是实现一个或者是n个线程需要等待其它线程完成某项操作之后，才能继续执行
 *      CyclicBarrier 主要是多个线程之间相互等待，直到所有的线程都满足了条件之后才能继续执行
 *
 *      CyclicBarrier能处理更复杂的业务场景，计算错误后还能重置计数器并且让线程重新执行。
 *
 *      使用情景：我们可以计算平均值的时候，把大量的数据分成几个部分，然后用多个线程做处理，得到每一部分的平均值
 *      之后，再执行汇总算出总体平均值。
 */
@Slf4j
public class CyclicBarrierExample1 {

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

    }

    /**
     * 多个线程竞争
     */
    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        cyclicBarrier.await();
        log.info("{} continue execute", threadNum);
    }

}
