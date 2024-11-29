package com.liguo.demo.core.study.thread.interrupt.case1;

/**
 * 如果一个线程在调用 wait()、sleep() 或 join() 等方法时被中断，那么它会抛出 InterruptedException 并退出这些方法。线程在捕获异常后，可以选择处理异常或终止执行。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/31 10:40
 * @since 0.0.1
 */
public class MyTaskMainTest {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyTask());
        thread.start();

        // 主线程等待一段时间后中断
        try {
            Thread.sleep(1000); // 等待1秒

            // 这里thread线程正在sleep，中断会抛出InterruptedException
            thread.interrupt(); // 中断线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
