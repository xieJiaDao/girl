package com.xiejiadao.girl.concurrency.concurrencyextend;

/**
 * 并发最佳实践：
 *  （1）使用本地变量（局部变量）
 *  （2）使用不可变类：java 中八个基本类型的包装类和 String 类都属于不可变类，而其他的大多数类都属于可变类。
 *   (3) 最小化锁的作用域范围：S=1/(1-a+a/n)
 *      其中，a为并行计算部分所占比例，n为并行处理结点个数。
 *      这样，当1-a=0时，(即没有串行，只有并行)最大加速比s=n；
 *      当a=0时（即只有串行，没有并行），最小加速比s=1；当n→∞时，
 *      极限加速比s→ 1/（1-a），这也就是加速比的上限。
 *      例如，若串行代码占整个代码的25%，则并行处理的总体性能不可能超过4。
 *      这一公式已被学术界所接受，并被称做“阿姆达尔定律”，也称为“安达尔定理”(Amdahl law)。
 *  （4）使用线程池的Executor，而不是直接new Thread() 因为线程的创建和销毁，耗费性能。
 *  （5）宁可使用同步，也不要使用线程的wait 和 notify.
 *  （6）使用BlockingQueue实现生产消费模式
 *  （7）使用并发集合，而不是加了锁的同步集合。比如用ConcurrentHashMap,而不是使用HashTable
 *  （8）使用Semaphore 创建有界的访问
 *  （9）宁可使用同步代码块，也不要使用同步方法。
 *  （10）避免使用static变量，如果要使用，最好使用final。
 */
public class BestWayConcurrent {

}
