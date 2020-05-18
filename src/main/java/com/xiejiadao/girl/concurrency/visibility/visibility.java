package com.xiejiadao.girl.concurrency.visibility;

/**
 * 可见性
 *
 * 导致共享变量在线程之间不可见的原因是：
 *
 * （1） 线程交叉执行
 * （2） 线程交叉执行 结合 重排序
 * （3） 共享变量更新后的值没有在工作内存与主存之间及时更新
 *
 *
 * @author: xyf
 * @date: 2020/5/18 22:53
 */
public class visibility {

    /**
     * JMM 关于synchronized 的两条规定：
     *
     * （1） 线程解锁前， 必须把共享变量的最新值刷新到主内存
     *
     * （2） 线程加锁时， 将清空工作内存中共享变量的值，从而使用共享变量时需要从主内存中重新读取最新的值（注意，加锁与解锁是同一把锁）
     *
     */
    public void synchronizedTest() {

    }

    /**
     *
     * volatile 如何实现内存可见性
     *
     *      内存屏障 和 禁止指令重排序优化
     *
     * （1）对于volatile变量写操作时， 会在写操作后加入一条store屏障指令，将本地工作内存中的共享变量刷新到主内存
     *
     * （2）对volatile变量读操作时， 会在读取操作前加入一条load屏障指令，从主内存中读取共享变量
     *
     * （3）
     *
     *
     *
     *
     */
    public void volatileTest() {

    }

}
