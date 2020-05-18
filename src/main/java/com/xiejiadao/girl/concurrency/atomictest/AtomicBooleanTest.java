package com.xiejiadao.girl.concurrency.atomictest;

import com.xiejiadao.girl.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 可以保证一段代码只执行一次
 * @author: xyf
 * @date: 2020/5/18 22:02
 */
@ThreadSafe
@Slf4j
public class AtomicBooleanTest {

    private static AtomicBoolean isHappened = new AtomicBoolean(false);

    public static void main(String[] args) {

        new Thread(()-> {
            if (isHappened.compareAndSet(false, true)) {
                log.info("执行了第一次");
            }
        }).start();
        new Thread(()-> {
            if (isHappened.compareAndSet(false, true)) {
                log.info("执行了第二次");
            }
        }).start();

    }



}
