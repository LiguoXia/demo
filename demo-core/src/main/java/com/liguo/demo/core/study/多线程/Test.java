package com.liguo.demo.core.study.多线程;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/2/5 14:25
 * @since 0.0.1
 */
public class Test {

    // 主线程结束了,用户线程(用户编写线程，如threadName)还在运行,jvm存活
    // 没有用户线程了,都是守护线程,jvm 结束
    public static void main(String[] args) {
        Thread threadName = new Thread(() -> {
            System.out.println(Thread.currentThread().getName()
                    + "::" + Thread.currentThread().isDaemon());
            while (true) {
                // System.out.println("1");
            }
        }, "threadName");
        threadName.setDaemon(true);
        threadName.start();//用户线程

        System.out.println(Thread.currentThread().getName() + "::" + "over");//主线程
    }


    public static void hh(String[] args) {
        System.out.println("");
    }
}


