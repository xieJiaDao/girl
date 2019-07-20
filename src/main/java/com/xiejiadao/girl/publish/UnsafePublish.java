package com.xiejiadao.girl.publish;

import com.xiejiadao.girl.annotations.NotThreadSafe;

import java.util.Arrays;

/**
 *  发布对象
 * @author: xyf
 * @date: 2019/7/17 23:20
 */
@NotThreadSafe
public class UnsafePublish {

    private String[] states = {"a","b","c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        //通过new 发布了一个UnsafePublish类的实例
        UnsafePublish unsafePublish = new UnsafePublish();
        //通过public getStates()方法，得到实例的私有域（私有属性）的引用。
        String[] states = unsafePublish.getStates();
        System.out.println(Arrays.toString(states));

        //得到引用后，就可以在其它任何一个线程里更改这个私有域。
        states[0] = "d";
        System.out.println(Arrays.toString(states));
        unsafePublish.getStates()[0] = "d";
        System.out.println(Arrays.toString(unsafePublish.getStates()));

        UnsafePublish unsafePublish1 = new UnsafePublish();
        System.out.println(Arrays.toString(unsafePublish1.getStates()));

    }

}
