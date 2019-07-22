package com.xiejiadao.girl.concurrency.aqs.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock:
 *  读锁 写锁 乐观读
 *
 *  一个StampedLock是由版本 和 模式组成。
 *
 *  当并发不大的时候，使用synchronized 就行、
 *  当并发多，但是我们能预估的 可以用RenentrantLock
 *
 *  适合自己场景的才是最好的。
 */
public class StampedLockExample {
    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();

        //加写锁
        long stamp = stampedLock.writeLock();

        //释放写锁需要加锁 时返回的stamp
        stampedLock.unlock(stamp);

    }
}
