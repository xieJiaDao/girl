package com.xiejiadao.girl.concurrency.extend;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Future的测试：主要是常见的线程实现，我们并不能获取到线程执行结果。
 *
 */
@Slf4j
public class FutureTaskExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.info("执行MyCallable的call()方法");
                Thread.sleep(5000);
                return "执行成功";
            }
        });

        new Thread(futureTask).start();

        //do other things
        Thread.sleep(2000);
        String futureTaskResult = futureTask.get();
        log.info("futureTask 执行结果：{}",futureTaskResult);
    }
}
