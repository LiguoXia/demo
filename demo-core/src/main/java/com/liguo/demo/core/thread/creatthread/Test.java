package com.liguo.demo.core.thread.creatthread;

/**
 * <pre>
 * tag：
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/26 23:34
 * @since 0.0.1
 */
public class Test {
    public static void main(String[] args) {
        Thread a = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }, "myThread");
        a.start();
    }
}
