package com.liguo.demo.core.study.锁.reentrantLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/3/7 21:49
 * @since 0.0.1
 */
@Slf4j
public class InterruptibleLockExample {
    private Lock lock = new ReentrantLock();

    public void performTask() throws InterruptedException {
        try {
            // 尝试获取锁，响应中断
            lock.lockInterruptibly();
            //lock.lock();
            // 在临界区内执行任务
            log.info(Thread.currentThread().getName() + " is performing the task...");
            // 模拟任务执行时间
            Thread.sleep(3000);
        } finally {
            // 释放锁
            log.info("解锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        InterruptibleLockExample example = new InterruptibleLockExample();

        // 创建一个线程执行任务
        Thread taskThread = new Thread(() -> {
            try {
                example.performTask();
            } catch (InterruptedException e) {
                log.info((Thread.currentThread().getName() + " was interrupted while waiting for the lock."), e);
            }
        });

        // 启动任务线程
        taskThread.start();

        // 主线程等待一段时间后中断任务线程
        Thread.sleep(1000);
        taskThread.interrupt();
        // Thread.currentThread().interrupt();

    }
}
