package com.xiejiadao.girl.concurrency.aqs.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionExample {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("wait signal");
                condition.await();//其实就把锁释放了。加入了Condition的等待队列中，等待信号。
            } catch (InterruptedException e) {
                log.error("异常：", e);
            }
            log.info("get signal");
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                log.error("异常：", e);
            }
            condition.signalAll();// you
            log.info("send signal");
            reentrantLock.unlock();
        }).start();
    }
}
