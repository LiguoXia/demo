package com.liguo.demo.core.thread.creatthread;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/3 22:19
 * @since 0.0.1
 */
@Slf4j
public class JoinThread {
    public static void main(String[] args) {
        Thread B = new Thread(() -> {
            log.info("线程B开始执行");
            //休眠两秒
            ThreadUtil.sleep(2 * 1000);
            log.info("线程B执行完成");
        });

        new Thread(() -> {
            log.info("线程A开始执行");
            //休眠两秒
            ThreadUtil.sleep(2 * 1000);
            try {
                B.start();
                B.join();
            } catch (InterruptedException e) {
                log.error("异常:", e);
            }
            log.info("线程A执行完成");
        }).start();
        log.info("主线程结束");
    }
}
