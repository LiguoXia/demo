package com.liguo.demo.core.study.锁.shareLock;

import com.liguo.demo.core.thread.creatthread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 * 共享读写锁
 *
 * 共享锁的主要作用是在允许多个线程同时读取共享资源的情况下，确保数据的一致性和安全性。以下是共享锁的一些重要意义：
 *
 * 提高并发性：共享锁允许多个线程同时访问同一资源进行读取，而不需要等待其他线程释放锁。这在读取操作远多于写入操作的场景中尤其有效，能够提高系统的并发处理能力。
 *
 * 防止数据不一致：即使多个线程可以并发读取数据，共享锁仍然确保在读取期间不允许写入操作发生，这样可以防止数据被修改而导致的读取不一致性。
 *
 * 优化性能：在高并发读操作的场景中，使用共享锁可以减少锁竞争，提高系统的整体性能，特别是在读多写少的情况下。
 *
 * 避免死锁：共享锁的机制通常较为简单，可以帮助减少复杂的锁管理，从而降低死锁的风险。
 *
 * 总的来说，共享锁为多个线程提供了安全的读取机制，同时仍然确保了数据的一致性和完整性。
 *
 *
 * 说明：
 * 1、一个线程获取了读锁，其他线程还能获取读锁，但是不能获取写锁
 * 2、一个线程获取了写锁，其他线程不能获取读或者写
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/31 9:07
 * @since 0.0.1
 */
@Slf4j
public class SharedLockExample {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private String data = "init";

    // 读取数据的方法
    public String readData(int i) {
        lock.readLock().lock();  // 获取共享锁
        try {
            log.info("读:{}", i);
            return data; // 读取数据
        } finally {
            lock.readLock().unlock(); // 释放共享锁
        }
    }

    // 写入数据的方法
    public void writeData(String newData, int i) {
        lock.writeLock().lock(); // 获取独占锁
        try {
            log.info("写:{}", i);
            ThreadUtil.sleep(3000);
            data = newData; // 写入数据
        } finally {
            lock.writeLock().unlock(); // 释放独占锁
        }
    }

    public static void main(String[] args) {
        SharedLockExample example = new SharedLockExample();
        log.info("1");
        // 启动多个读取线程
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                example.writeData("New Data", i);
                continue;
            }
            int finalI = i;
            new Thread(() -> {
                System.out.println("Read: " + example.readData(finalI));
            }).start();
        }
        log.info("2");
        // 启动一个写入线程
        new Thread(() -> {
            example.writeData("New Data", 99);
            System.out.println("Data written");
        }).start();
        log.info("3");
    }
}
