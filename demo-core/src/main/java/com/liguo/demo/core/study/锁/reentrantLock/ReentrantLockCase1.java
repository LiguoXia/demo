package com.liguo.demo.core.study.锁.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/11 15:44
 * @since 0.0.1
 */
public class ReentrantLockCase1 {
    // 创建ReentrantLock对象
    Lock lock = new ReentrantLock();

    Lock fairLock = new ReentrantLock(true); // 公平锁
    Lock unfairLock = new ReentrantLock();    // 非公平锁

    public void test() {
        // 1
        if (lock.tryLock()) {
            try {
                // 成功获取锁的处理逻辑
            } finally {
                lock.unlock();
            }
        } else {
            // 未获取到锁的处理逻辑
        }

        // 2
        lock.lock(); // 获取锁
        try {
            // 临界区 - 执行需要同步的代码
        } finally {
            lock.unlock(); // 释放锁，确保在发生异常时也能释放锁
        }
    }
}
