package com.liguo.demo.core.thread.creatthread;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/2 22:15
 * @since 0.0.1
 */
public class RunnableDemo2 {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("线程输出");
            //休眠两秒
            ThreadUtil.sleep(2 * 1000);
        }).start();
    }
}
