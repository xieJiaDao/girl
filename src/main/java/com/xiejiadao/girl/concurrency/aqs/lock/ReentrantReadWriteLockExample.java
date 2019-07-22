package com.xiejiadao.girl.concurrency.aqs.lock;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock 的核心是：
 *          在没有任何读写锁的情况我们才能获取写锁
 *
 *          所以，当我们读取线程比较多的时候，有可能就把写锁给阻塞很久,饥饿。
 */
@Slf4j
public class ReentrantReadWriteLockExample {

    private final static Map<String, Data> map = new TreeMap<>();

    private final static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private final static Lock readLock = readWriteLock.readLock();

    private final static Lock writeLock = readWriteLock.writeLock();


    public Data get(String key) {
        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Set<String> getAllKeys() {
        readLock.lock();
        try {
            return map.keySet();
        } finally {
            readLock.unlock();
        }
    }

    public void put(String key, Data value) {
        writeLock.lock();

        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }


    class Data {

    }
}
