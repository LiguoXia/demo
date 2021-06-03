package com.liguo.demo.core.thread.threadsafe;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/4 0:09
 * @since 0.0.1
 */
public class VolatileTest {
    public static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println("准备数据");
            while (!flag) {

            }
            System.out.println("成功");
        }).start();
        Thread.sleep(2000);
        new Thread(() ->
                prep()
        ).start();
    }

    public static void prep() {
        System.out.println("开始准备数据");
        flag = true;
        System.out.println("数据准备完成");
    }
}
