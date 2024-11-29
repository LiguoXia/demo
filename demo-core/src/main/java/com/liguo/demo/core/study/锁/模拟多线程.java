package com.liguo.demo.core.study.锁;

import com.liguo.demo.core.factory.DemoThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/12 20:26
 * @since 0.0.1
 */
@Slf4j
public class 模拟多线程 {

    private final Object lock = new Object();

    private static final ThreadPoolExecutor executorService = new ThreadPoolExecutor(
            100,
            100,
            0,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            new DemoThreadFactory("thread-lock"),
            new ThreadPoolExecutor.CallerRunsPolicy());
    public static int a = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            executorService.submit(() -> {
                add(finalI);
            });
        }
    }

    public synchronized static void add(int i) {
        a++;
        log.info("第:{}次, a:{}", i, a);
    }

    /**
     * 同步实例方法
     *
     * 作用范围： 整个方法被同步，即使方法内有多个同步块，也会被同一把锁保护。
     * 锁对象：    锁对象是调用该方法的实例对象。
     * 实例级别锁： 多个实例的同步方法之间互不影响。
     */
    public synchronized void myMethod1() {
        // 同步代码块
        // ...
    }

    /**
     * 同步代码块：
     *
     * 作用范围： 仅同步代码块内的代码。
     * 锁对象： 锁对象是括号内指定的对象，通常是 this。
     * 实例级别锁： 与同步方法相似，锁定调用该方法的实例对象。
     */
    public void myMethod2() {
        synchronized (this) {
            // 同步代码块
            // ...
        }
    }

    /**
     * 同步静态方法：
     *
     * 作用范围： 整个静态方法被同步，即使方法内有多个同步块，也会被同一把锁保护。
     * 锁对象： 锁对象是类的 Class 对象。
     * 类级别锁： 所有实例共享同一把锁，静态方法会影响同一类的所有实例对象。
     */
    public static synchronized void myStaticMethod1() {
        // 同步代码块
        // ...
    }

    /**
     * 同步代码块（使用自定义锁对象）：
     *
     * 作用范围： 仅同步代码块内的代码。
     * 锁对象： 锁对象是括号内指定的对象，可以是自定义对象。
     * 自定义锁： 可以更灵活地使用自定义的对象作为锁。
     */
    public void myMethod3() {
        synchronized (lock) {
            // 同步代码块
            // ...

        }
    }
}
