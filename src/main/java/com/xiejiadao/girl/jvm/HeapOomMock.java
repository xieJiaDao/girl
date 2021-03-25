package com.xiejiadao.girl.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 堆内存是 JVM 所有线程共享的部分，在虚拟机启动的时候就已经创建。
 * 所有的对象和数组都在堆上进行分配。这部分空间可通过 GC 进行回收。
 * 当申请不到空间时会抛出 OutOfMemoryError。
 * 下面我们简单的模拟一个堆内存溢出的情况：
 *
 * @author yafei
 * @date 2021/03/25
 */
public class HeapOomMock {
    /**
     * Vm options: 设置为 -Xms10m -Xmx10m
     * 结果是8次，说明别的占用了堆内存部分空间。
     * @param args
     */
    public static void main(String[] args) {
        boolean flag = true;
        int i = 0;
        List<byte[]> list = new ArrayList<>();
        while (flag) {
            try {
                i++;
                list.add(new byte[1024 * 1024]);//1kb*1kb = 1Mb
            } catch (Throwable e) {
                System.out.println("执行次数："+ i);
                flag = false;
                e.printStackTrace();
            }
        }
    }
}
