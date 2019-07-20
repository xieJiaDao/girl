package com.xiejiadao.girl.entity;

/**
 * @author: xyf
 * @date: 2019/4/14 20:00
 */
public class LittleGirl extends Girl {
    public void fly () throws NullPointerException{
        if (true) {
            super.fly();
            throw new RuntimeException();
        }
        System.out.println("LittleGirl");
    }

    public static void main(String[] args) {
       LittleGirl littleGirl = new LittleGirl();
       littleGirl.fly();

    }
}
