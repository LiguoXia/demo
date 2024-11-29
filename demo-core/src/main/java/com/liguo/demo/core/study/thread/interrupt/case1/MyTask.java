package com.liguo.demo.core.study.thread.interrupt.case1;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/31 10:39
 * @since 0.0.1
 */
public class MyTask implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("Thread going to sleep.");
            Thread.sleep(10000); // 线程将进入睡眠状态
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted during sleep.");
            // 在这里处理清理或其他逻辑
            return;
        }

        // 如果没有被中断，将继续执行
        System.out.println("Task completed.");
    }
}