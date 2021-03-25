package com.xiejiadao.girl.jvm;

/**
 * StackOverflowError
 * 模拟虚拟机栈溢出代码
 *
 * 虚拟机栈：每个线程有一个私有的栈，随着线程的创建而创建。
 * 栈里面存着的是一种叫“栈帧”的东西，每个方法会创建一个栈帧，
 * 栈帧中存放了局部变量表（基本数据类型和对象引用）、操作数栈、方法出口等信息。
 * 栈的大小可以固定也可以动态扩展。当栈调用深度大于JVM所允许的范围，
 * 会抛出StackOverflowError的错误，不过这个深度范围不是一个恒定的值，
 * 我们通过下面这段程序可以测试一下这个结果：
 *
 * @author yafei
 * @date 2021/03/25
 */
public class StackErrorMock {
    public static int stackDeep = 1;
    public static void call() {
        stackDeep++;
        call();
    }

    /**
     * stackDeep 是动态变化的
     * @param args
     */
    public static void main(String[] args) {
        try {
            call();
        } catch (Throwable e) { // 别用Exception，捕获不到异常的，这个是error。
            System.out.println("stack deep:" + stackDeep);
            e.printStackTrace();
        }
    }
}
