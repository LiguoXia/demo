package com.liguo.demo.core.thread.interrupt;

/**
 * <pre>
 * tag：
 * 多线程协作：中断配合共享变量
 *
 * 使用共享变量 stop 配合 interrupt()，确保线程安全退出。
 * 即使线程未进入阻塞状态，stop 变量也会停止其任务。
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/27 12:03
 * @since 0.0.1
 */
public class InterruptWithSharedVariable {
    private static volatile boolean stop = false;

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!stop) {
                System.out.println("Thread is running...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Thread was interrupted!");
                    break;
                }
            }
            System.out.println("Thread is stopping...");
        });

        thread.start();

        try {
            Thread.sleep(2000); // 主线程等待 2 秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stop = true; // 设置共享变量通知线程停止
        thread.interrupt(); // 额外触发中断
    }
}
