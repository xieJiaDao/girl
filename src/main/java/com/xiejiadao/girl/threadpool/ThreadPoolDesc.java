package com.xiejiadao.girl.threadpool;

import java.util.concurrent.*;

/**
 *
 * @author: xyf
 * @date: 2019/7/18 21:57
 */
public class ThreadPoolDesc {
    /**
     * new Thread() 的弊端 ：
     * (1)每次都new Thread() 新建对象，性能差；
     * (2)线程缺乏统一管理。可能无限制的新建线程，相互竞争，有可能占用过多的系统资源，导致死机 或者 OOM
     * (3)thread缺少很多功能：定期执行、线程中断等等
     */
    public static Thread newThread(){

        Thread thread = new Thread(() -> {
            System.out.println("我是测试下线程new的成功不。");
        });
        return thread;
    }

    /**
     * 线程池的好处：
     * （1）重用存在的线程，减少对象的创建，消亡的开销，性能更好。
     * （2）可以有效控制最大并发线程数，提高系统资源利用率，同时可以避免过多资源竞争，避免阻塞。
     * （3）提供定时执行，定期执行，单线程，并发数控制等功能
     */
    public static ThreadPoolExecutor getThreadPoolExecutor() {
        /**
         * (1)corePoolSize 核心线程数量 --如果线程池中线程数量还没有达到corePoolSize，
         * 当来了一个新的任务的时候，即使有空闲的线程，也会创建一个新的线程去执行新的任务
         *
         * (2)maximumPoolSize 最大线程数量
         * --只有当workQueue满了的时候，才会创建核心线程之外的线程去执行任务。
         *
         * (5)workQueue 阻塞队列
         * -- corepoolSize < threadNumber < corepoolSize + workQueue.size  ，新来的任务会放进阻塞队列  maximumPoolSize
         *
         * corepoolSize + workQueue.size < threadNumber < maximumPoolSize , 新来的任务会创建核心线程之外的线程。
         *
         * (3)keepAliveTime 核心线程之外的线程没有执行任务的存活时间
         * (4)TimeUnit 存活时间单位
         *（6）ThreadFactory 线程工厂，用来创建线程 ，
         * --有默认的线程工厂，默认工厂创建线程的时候线程的优先级别是相同的，并且是非守护的线程，同时也设置线程的名称
         *
         * (7)RejectedExecutionHandeler --当workQueue满了且线程数量达到maximumPoolSize的时候，新任务到来的拒绝策略
         * --总共四种策略：默认的是1抛出异常;2用调用者所在的线程去执行任务;3直接丢弃队列中最靠前的任务，并执行当前任务4直接丢弃新的任务
         *
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1,1,10, TimeUnit.HOURS,new LinkedBlockingDeque<>(1));
        return threadPoolExecutor;
    }

    /**
     * 线程池的五种状态：
     * (1) running 状态能接收新提交的任务，并且也能处理阻塞队列中的任务；
     *
     * (2)shutdown 关闭状态，是running状态的时候，执行shutdown()方法
     * --当处于shutdown状态的时候，线程池不能接收新的任务，但是可以处理阻塞队列中的任务。
     *
     * (3)stop 是running状态或者shutdown装填的时候 执行shutdownNow()方法
     * --stop状态，既不能接收新的任务，也不能处理阻塞队列中的任务。它会中断正在处理任务的线程。
     *
     * (4)tidying 装填 (4-1)线程池处于shutdown时候，如果阻塞队列为空，且工作线程数量为0
     *  (4-2)或者是线程池处于stop状态的时候，工作线程数量为0，就会 变成tidying状态。
     *
     *  (5)terminated 终止状态，tidying状态的时候执行terminated()方法
     *
     */

    /**
     * 提交任务的两种方法：
     * execute() : 提交任务，交给线程池执行；
     * submit() : 提交任务，交给线程池执行，能够返回执行结果，execute + future
     * <p>
     * 关闭线程池的两种方法：
     * shutdown(): 关闭线程池，等待任务都执行完；
     * shutdownNow(): 关闭线程池，终止正在执行任务的线程，不等待任务执行完。
     * <p>
     * 监控线程池的方法:
     * getTaskCount(): 获取线程池中已经执行和未执行的任务总数。
     * getCompletedTaskCount(): 获取获取线程池已经完成的任务数量。
     * getPoolSize(): 获取线程池的线程总量。
     * getActiveCount(): 获取线程池中正在执行任务的线程数量。
     */


    /**
     * 测试可以缓存的线程池：
     * new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                                       60L, TimeUnit.SECONDS,
     *                                       new SynchronousQueue<Runnable>());
     * 优点灵活方便。
     *
     */
    public static void CachedThreadPoolTest() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            //final定义的变量，匿名内部类可以访问到。
            final int index = i;
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("task:"+index);
                }
            });
        }
        cachedThreadPool.shutdown();
    }

    /**
     * FixedThreadPool
     *
     * new ThreadPoolExecutor(nThreads, nThreads,
     *                                       0L, TimeUnit.MILLISECONDS,
     *                                       new LinkedBlockingQueue<Runnable>())
     *  该线程池不关闭的话，会一直处于running中。
     */
    public static void fixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(() -> {
                System.out.println("当前线程是:"+Thread.currentThread().getName()+" task: "+index);
            });
        }
        fixedThreadPool.shutdown();
    }


    /**
     *scheduledThreadPool不是普通的线程池，它有调度的概念了。
     * new ScheduledThreadPoolExecutor(corePoolSize);
     *
     *
     *
     */
    public static void scheduledThreadPool() {
        ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        ((ScheduledExecutorService) scheduledThreadPool).schedule(() -> {
            System.out.println("aa");
        }, 3, TimeUnit.SECONDS);
    }




    public static void main(String[] args) {
//        newThread().start();
//        CachedThreadPoolTest();
//        fixedThreadPool();
        scheduledThreadPool();
    }


}
