package com.xiejiadao.girl.threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author: xyf
 * @date: 2020/4/28 21:56
 */
@Slf4j
public class TestCoolectGarbage {
    public static void main(String[] args) throws IOException, InterruptedException {
        Garbage garbage = new Garbage();
        log.info(garbage.toString());
        garbage = null;
        System.gc();
//        Thread.sleep(1000);
        System.in.read();//为什么要加这句话，因为main线程走完了，jvm整个退出，垃圾回收也会退出，所以用这个方法阻塞
    }
}
