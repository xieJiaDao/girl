package com.xiejiadao.girl.publish;

import com.xiejiadao.girl.annotations.ThreadSafe;

/**
 * 安全的发布对象的四种方法
 *
 * @author: xyf
 * @date: 2019/7/17 23:43
 */
@ThreadSafe
public class SafePublish {

    /**
     * 1.在静态初始化函数中，初始化一个对象的引用。
     */


    /**
     * 2.将一个对象的引用保存到volatile类型域，或者AtomicReference对象中。
     */

    /**
     * 将对象的引用保存到某个正确构造对象的final类型域中。
     */

    /**
     * 将对象的引用保存到一个由锁保存的域中。
     */
}
