package com.liguo.demo.core.study.锁.AQS.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/1 17:43
 * @since 0.0.1
 */
public class ProducerConsumer {
    private static final int CAPACITY = 5;
    private final int[] buffer = new int[CAPACITY];
    private int count, head, tail;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public void produce(int value) throws InterruptedException {
        lock.lock();
        try {
            while (count == CAPACITY) {
                notFull.await(); // 等待缓冲区不满
            }
            buffer[tail] = value;
            tail = (tail + 1) % CAPACITY;
            count++;
            notEmpty.signal(); // 唤醒消费者
        } finally {
            lock.unlock();
        }
    }

    public int consume() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await(); // 等待缓冲区不空
            }
            int value = buffer[head];
            head = (head + 1) % CAPACITY;
            count--;
            notFull.signal(); // 唤醒生产者
            return value;
        } finally {
            lock.unlock();
        }
    }
}
