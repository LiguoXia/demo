package com.liguo.demo.core.thread.newthread;

import lombok.extern.slf4j.Slf4j;

/**
 * 新开线程的方法
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/6/13 9:58
 * @since 0.0.1
 */
@Slf4j
public class NewThread {
    public static void main(String[] args) {
        log.info("主线程");
        new Thread(() -> new1(), "threadName").start();
    }

    private static void new1() {
        log.info("新线程");
    }
}
