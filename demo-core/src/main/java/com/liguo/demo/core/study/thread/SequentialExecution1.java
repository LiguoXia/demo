package com.liguo.demo.core.study.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 共享的 lock 对象：
 * <p>
 * 使用一个共享的 lock 对象，通过 synchronized 关键字确保对 currentThread 的操作是原子的，防止多个线程同时修改它的值。
 * 线程逻辑：
 * <p>
 * 每个线程在进入临界区之前，都会使用 synchronized (lock) 同步块锁定 lock 对象。
 * 在同步块内，线程会检查当前轮到哪个线程执行，如果不是自己，就会调用 lock.wait() 方法等待。
 * 如果是自己轮到执行，就执行线程的逻辑，然后更新 currentThread 的值，并使用 lock.notifyAll() 唤醒其他等待的线程。
 * 循环等待：
 * <p>
 * 使用 while (threadNumber != currentThread) 循环等待的方式，确保线程在不是自己轮到执行时一直等待。
 * 唤醒其他等待的线程：
 * <p>
 * 在更新完 currentThread 的值后，使用 lock.notifyAll() 唤醒其他等待的线程，让它们有机会继续执行。
 * 这样，通过使用 wait() 和 notifyAll()，实现了线程按照指定的顺序执行。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/11/30 21:23
 * @since 0.0.1
 */
@Slf4j
public class SequentialExecution1 {
    private static final int THREAD_COUNT = 4;
    private static final Object lock = new Object();
    private static int currentThread = 0;

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNumber = i;
            new Thread(() -> {
                try {
                    synchronized (lock) {
                        while (threadNumber != currentThread) {
                            lock.wait(); // 等待轮到自己执行
                        }

                        // 这里是你的线程逻辑
                        System.out.println("线程 " + threadNumber + " 执行了。");
                        log.info("当前:{}", currentThread);
                        currentThread = (currentThread + 1) % THREAD_COUNT;
                        log.info("下一个:{}", currentThread);
                        lock.notifyAll(); // 唤醒其他等待的线程
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
