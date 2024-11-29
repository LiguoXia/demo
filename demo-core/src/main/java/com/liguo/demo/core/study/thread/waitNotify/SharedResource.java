package com.liguo.demo.core.study.thread.waitNotify;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/17 18:56
 * @since 0.0.1
 */
@Slf4j
public class SharedResource {
    private boolean condition = false;

    public synchronized void waitForCondition() {
        while (!condition) {
            log.info("进入死循环");
            try {
                // 当前线程进入等待状态
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("Condition is true. Continue processing.");
    }

    public synchronized void setConditionTrue() {
        this.condition = true;
        // 唤醒等待的线程
        notifyAll();
    }
}
