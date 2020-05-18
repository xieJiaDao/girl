package com.xiejiadao.girl.concurrency.atomictest;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 主要解决CAS的ABA问题
 *
 * 使用版本号来解决
 *
 * @author: xyf
 * @date: 2020/5/18 21:47
 */
public class AtomicStampReferenceTest {

    private static AtomicStampedReference<Integer> stampedReferenceAtomic = new AtomicStampedReference<>(100, 0);

    public static void main(String[] args) {

    }


}
