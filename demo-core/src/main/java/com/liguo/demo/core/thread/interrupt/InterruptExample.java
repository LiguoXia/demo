package com.liguo.demo.core.thread.interrupt;

/**
 * <pre>
 * tag：线程中断
 * 响应中断标志
 *
 * 关键点
 * 中断标志：
 * interrupt() 不会强制终止线程，而是设置线程的中断标志。
 * 线程需要自己检查 isInterrupted() 方法或响应 InterruptedException 来决定如何处理中断。
 *
 * 阻塞操作：
 * 如果线程处于阻塞状态（如调用 sleep()、wait() 或 join()），中断会抛出 InterruptedException，并清除中断标志。
 *
 * 非阻塞操作：
 * 对于非阻塞状态，线程需要主动检查中断标志并做出响应。
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/27 11:53
 * @since 0.0.1
 */
public class InterruptExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Thread is running...");
                /*try {
                    Thread.sleep(500); // 模拟工作
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted during sleep!");
                    Thread.currentThread().interrupt(); // 重新设置中断标志
                }*/
            }
            System.out.println("Thread is stopping...");
        });

        thread.start();

        try {
            Thread.sleep(2000); // 主线程等待 2 秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt(); // 中断线程
    }
}
