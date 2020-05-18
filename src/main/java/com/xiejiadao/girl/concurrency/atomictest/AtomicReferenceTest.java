package com.xiejiadao.girl.concurrency.atomictest;

import com.xiejiadao.girl.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: xyf
 * @date: 2020/5/18 21:35
 */
@ThreadSafe
@Slf4j
public class AtomicReferenceTest {
    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        count.compareAndSet(0, 2);
        count.compareAndSet(0, 1);
        count.compareAndSet(1, 3);
        count.compareAndSet(2, 4);
        count.compareAndSet(3, 5);
        count.compareAndSet(4, 6);
        log.info("count:{}", count);
    }
}
