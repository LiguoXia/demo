package com.liguo.demo.core.study.锁;

import com.liguo.demo.core.thread.creatthread.ThreadUtil;

/**
 * 实例方法的锁，锁的是实例，不同实例不会影响
 * 锁静态对象锁的是Class对象，锁的是这个类
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/17 17:04
 * @since 0.0.1
 */
public class TestLockMethod {
    public static void main(String[] args) {
        test2();
    }

    // 一个加锁一个不加锁
    public static void test1() {
        // 只有一个实例对象 lm 方法之间也存在锁竞争
        LockMethod lm = new LockMethod();
        new Thread(() -> {
            lm.lockMethod("1");
        }).start();
        ThreadUtil.sleep(1000);
        new Thread(() -> {
            lm.unLockMethod("2");
        }).start();
    }

    // 两个加方法锁
    public static void test2() {
        // 只有一个实例对象 lm 方法之间也存在锁竞争
        LockMethod lm = new LockMethod();
        LockMethod lm1 = new LockMethod();
        new Thread(() -> {
            lm.lockMethod("1");
        }).start();
        ThreadUtil.sleep(1000);
        new Thread(() -> {
            lm1.lockMethod1("2");
        }).start();
    }

    // 两个加静态锁
    public static void test3() {
        // 只有一个实例对象 lm 方法之间也存在锁竞争
        LockMethod lm = new LockMethod();
        LockMethod lm1 = new LockMethod();
        new Thread(() -> {
            lm.lockMethod2("1");
        }).start();
        ThreadUtil.sleep(1000);
        new Thread(() -> {
            lm1.lockMethod3("2");
        }).start();
    }
}
