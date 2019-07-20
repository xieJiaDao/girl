package com.xiejiadao.girl.publish;

import lombok.extern.slf4j.Slf4j;

/**
 * 对象逸出
 * @author: xyf
 * @date: 2019/7/17 23:31
 */
@Slf4j
public class Escape {

    private int isCanBeEscape = 0;

    public Escape() {
        log.info("Escape()");
        new InnerClass();
    }

    private class InnerClass {
        /**
         * 调用new Escape()构造方法的时候，就会调用 内部类InnerClass的构造。
         * 这个时候，Escape实例还没有完全构造完成，但是我们就调用Escape.this.isCanBeEscape;
         *
         * 这样就符合对象逸出的概念：
         *  一种错误的发布。当一个对象还没有构造完成的时候，就使它被其它线程可见。
         *  这样会导致，一个线程获取到过期的属性。
         *  或者线程获取到最新的，可是对象还是旧的属性
         *  如果一个对象是可变对象，那么它就要被安全发布才行。
         */
        public InnerClass() {
            log.info("{}",Escape.this.isCanBeEscape);
        }
    }

    public static void main(String[] args) {
        Escape escape = new Escape();
    }

}
