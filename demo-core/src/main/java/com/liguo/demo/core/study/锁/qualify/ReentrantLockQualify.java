package com.liguo.demo.core.study.锁.qualify;

import com.liguo.demo.core.thread.creatthread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/31 14:53
 * @since 0.0.1
 */
@Slf4j
public class ReentrantLockQualify {
    // 若 ReentrantLock是static，则属于类，加锁锁的是类对象，所有实例都会有影响
    private static final ReentrantLock lock = new ReentrantLock();

    // 若 ReentrantLock 是静态属性（Static Field），加锁锁的是类对象，所有实例都会有影响
    // 若 ReentrantLock 是实例属性（Instance Field）：加锁锁的是类的实例对象，锁之间不会影响
    //private final ReentrantLock lock = new ReentrantLock();

    public void myMethoda1() {
        lock.lock(); // 获取锁
        try {
            log.info("获取锁后");
            ThreadUtil.sleep(50000);
        } finally {
            log.info("解锁");
            lock.unlock(); // 释放锁
        }
    }

    public void myMethod() {
        lock.lock(); // 获取锁
        try {
            log.info("获取锁后");
        } finally {
            log.info("解锁");
            lock.unlock(); // 释放锁
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockQualify rlq1 = new ReentrantLockQualify();
        ReentrantLockQualify rlq2 = new ReentrantLockQualify();
        Thread a1 = new Thread(() -> {
            rlq1.myMethoda1();
        }, "aaaaaaaa1");
        a1.start();

        new Thread(() -> {
            rlq2.myMethod();
        }, "aaaaaaaa2").start();

        new Thread(() -> {
            rlq2.myMethod();
        }, "aaaaaaaa3").start();

        new Thread(() -> {
            rlq2.myMethod();
        }, "aaaaaaaa4").start();


        new Thread(() -> {
            rlq2.myMethod();
        }, "aaaaaaaa5").start();
    }
}
