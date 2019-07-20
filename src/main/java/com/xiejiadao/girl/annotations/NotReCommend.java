package com.xiejiadao.girl.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *课程里用来标记 【不推荐的】 的类 或者 是写法/
 *
 * @author: xyf
 * @date: 2019/7/14 17:27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NotReCommend {

    String value() default "";

}
