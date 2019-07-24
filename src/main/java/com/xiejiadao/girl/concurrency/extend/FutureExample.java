package com.xiejiadao.girl.concurrency.extend;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Future测试
 */
@Slf4j
public class FutureExample {


    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("执行MyCallable的call()方法");
            Thread.sleep(5000);
            return "执行成功";
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        Future<String> future = cachedThreadPool.submit(new MyCallable());

        //可以先做别的任务，然后，在后面想使用 线程执行结果的时候再使用future获取、
        Thread.sleep(1000);

        //这个会阻塞，一直到获取结果
        String callableResult = future.get();

        log.info("Future 获取到执行结果：{}", future.get());


    }

}
