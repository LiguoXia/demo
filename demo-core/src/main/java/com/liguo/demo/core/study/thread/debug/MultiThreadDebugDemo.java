package com.liguo.demo.core.study.thread.debug;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * tag：多线程 debug 切换
 * 说明: 左下角 Debugger-Frames选择线程
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/20 14:31
 * @since 0.0.1
 */
public class MultiThreadDebugDemo {
    public static void main(String[] args) {
        // 创建一个固定线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            int taskId = i; // 使用局部变量避免 Lambda 捕获问题
            executor.submit(() -> {
                // 添加断点到此处，调试不同线程的执行
                System.out.println("Task " + taskId + " is running on thread " + Thread.currentThread().getName());
                try {
                    // 模拟任务耗时
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("Task " + taskId + " was interrupted.");
                }
                System.out.println("Task " + taskId + " is completed.");
            });
        }

        // 关闭线程池
        executor.shutdown();
    }
}
