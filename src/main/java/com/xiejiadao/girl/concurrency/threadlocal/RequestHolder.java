package com.xiejiadao.girl.concurrency.threadlocal;

/**
 * 可以ThreadLocal 和 过滤器 配合使用
 *
 * 获取当前请求的用户，并把它存入ThreadLocal中，所以我们就可以在service dao 层都能使用了
 *
 * 之前得从controller层一层层传下去
 *
 * @author: xyf
 * @date: 2019/7/20 17:56
 */
public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);//set的时候，相当于是ThreadLocal中有个map，key就是当前线程，value就是我们set的id

    }

    public static Long getId() {
        return requestHolder.get();
    }

    /**
     * 移除操作，不做的话会一直存在，多了的话就会造成内存泄漏、
     */
    public static void remove() {
        requestHolder.remove();
    }

}
