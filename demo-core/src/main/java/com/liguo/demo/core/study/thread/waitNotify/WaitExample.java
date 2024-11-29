package com.liguo.demo.core.study.thread.waitNotify;

import com.liguo.demo.core.thread.creatthread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 使当前线程等待，直到其他线程调用同一个对象的 notify() 或 notifyAll() 方法。它主要用于实现线程间的通信和协作。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/17 18:59
 * @since 0.0.1
 */
@Slf4j
public class WaitExample {
    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        // 创建一个线程等待条件
        Thread waitingThread = new Thread(() -> {
            log.info("WaitingThread: Waiting for condition to be true.");
            sharedResource.waitForCondition();
        });

        // 创建一个线程等待条件
        Thread waitingThread1 = new Thread(() -> {
            log.info("WaitingThread:1 Waiting for condition to be true.");
            sharedResource.waitForCondition();
        });

        // 创建一个线程设置条件为true
        Thread settingThread = new Thread(() -> {
            // 模拟一些处理时间
            ThreadUtil.sleep(2000);
            log.info("SettingThread: Setting condition to true.");
            sharedResource.setConditionTrue();
        });

        // 启动线程
        waitingThread.start();
        waitingThread1.start();
        settingThread.start();
    }
}
