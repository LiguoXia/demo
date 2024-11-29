package com.liguo.demo.core.study.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 初始化：
 *
 * 使用 CountDownLatch 初始化，初始计数为1 (new CountDownLatch(1))。这意味着在其他线程继续执行之前，Latch 需要从1减少到0。
 * 主线程执行：
 *
 * 使用 for 循环创建了四个线程 (THREAD_COUNT)。
 * 为每个线程分配一个唯一的标识符 (threadNumber)，这实际上是线程的索引。
 * 线程逻辑：
 *
 * 每个线程在执行其逻辑之前调用 latch.await()。这个方法阻塞线程，直到Latch的计数达到零。由于初始计数是1，线程将在此等待。
 * 释放线程：
 *
 * 主线程通过调用 latch.countDown() 释放所有等待的线程。这会将Latch的计数减少1。
 * 结果，等待的线程从它们的 await() 调用中释放出来，然后继续执行它们的逻辑。
 * 线程执行输出：
 *
 * 线程逻辑通过打印语句表示 (System.out.println("线程 " + threadNumber + " 执行了。"))，将按照线程创建循环的顺序执行。
 * 这个机制确保线程按照期望的顺序执行它们的逻辑。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/11/30 21:19
 * @since 0.0.1
 */
@Slf4j
public class SequentialExecution {
    private static final int THREAD_COUNT = 4;
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNumber = i;
            new Thread(() -> {
                try {
                    latch.await(); // 等待主线程放行
                    // 你的线程逻辑
                    log.info("Thread " + threadNumber + " is executed.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        log.info("main count:{}", latch.getCount());
        // 放行所有等待的线程
        latch.countDown();
    }
}
