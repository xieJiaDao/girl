package com.xiejiadao.girl.threadlocal;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: xyf
 * @date: 2020/4/28 21:55
 */
@Slf4j
public class Garbage {
    //不建议重写finalize方法。这个方法是gc的时候调用的，如果重写后的方法执行时间过长，就会导致FGC，OOM
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        log.info("垃圾回收喽！");
    }

    public Garbage() {
        log.info("垃圾初始化了！");
    }

}
