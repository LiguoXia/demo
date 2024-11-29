package com.liguo.demo.core.study.java基础;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 14:18
 * @since 0.0.1
 */
public class String相关 {

    public static void main(String[] args) {

    }

    /**
     * String
     *     String是只读字符串，它并不是基本数据类型，而是一个对象。从底层源码来看是一个final类型的
     *     字符数组，所引用的字符串不能被改变，一经定义，无法再增删改。每次对String的操作都会生成
     *     新的String对象。
     *     private final char value[]
     *
     *     每次+操作 ： 隐式在堆上new了一个跟原字符串相同的StringBuilder对象，再调用append方法 拼
     *     接+后面的字符。
     *
     * StringBuffer
     *     对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。
     *     StringBuilder 并没有对方法进行加同步锁，所以是非线程安全的。
     *
     *
     */
    public static void str() {

    }
}
