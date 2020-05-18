package com.xiejiadao.girl.concurrency.atomictest;

import com.xiejiadao.girl.annotations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author: xyf
 * @date: 2020/5/18 21:39
 */
@ThreadSafe
@Slf4j
public class AtomicReferenceFieldUpdateTest {
    private static AtomicIntegerFieldUpdater<AtomicReferenceFieldUpdateTest> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicReferenceFieldUpdateTest.class, "count");
    @Getter
    public volatile int count = 100; // 必须由volatile修饰，且不能用static修饰

    public static void main(String[] args) {
        AtomicReferenceFieldUpdateTest test = new AtomicReferenceFieldUpdateTest();
        if (updater.compareAndSet(test, 100, 120)) {
            log.info("update success:{}", test.getCount());
        }
        if (updater.compareAndSet(test, 100, 120)) {
            log.info("update success:{}", test.getCount());
        } else {
            log.info("update failed:{}", test.getCount());
        }

    }

}
