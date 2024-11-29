package com.liguo.demo.core.thread.interrupt;

/**
 * <pre>
 * tag：
 * 非阻塞任务：主动检查中断状态
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/27 11:59
 * @since 0.0.1
 */
public class InterruptNonBlockingExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // 模拟执行非阻塞任务
                System.out.println("Performing a non-blocking task...");
                for (int i = 0; i < 100_000; i++) {
                    double temp = Math.sqrt(i); // 模拟计算
                }
                // 主动检查中断标志
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread was interrupted! Exiting...");
                    break;
                }
            }
        });

        thread.start();

        try {
            Thread.sleep(1000); // 主线程等待 1 秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt(); // 通知线程中断
    }
}
