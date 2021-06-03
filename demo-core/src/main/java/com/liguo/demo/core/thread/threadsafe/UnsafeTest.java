package com.liguo.demo.core.thread.threadsafe;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/4 0:14
 * @since 0.0.1
 */
@Slf4j
public class UnsafeTest {
    public volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[10];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    log.info("线程执行加法操作");
                    add4Lock();
                }
            });
            threads[i].start();
        }
        // 阻塞的方法，保证上面十个线程都执行完了
        for (Thread thread : threads) {
            thread.join();
        }

        log.info("num的值是:{}", num);
    }

    // 加锁
    public static void add() {
        num++;
    }

    // 加锁
    public synchronized static void add4Lock() {
        num++;
    }
}
