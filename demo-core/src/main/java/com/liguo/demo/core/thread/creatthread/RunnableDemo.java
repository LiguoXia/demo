package com.liguo.demo.core.thread.creatthread;

import lombok.extern.slf4j.Slf4j;

/**
 * 通过实现 Runnable 接口来创建线程
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/2 22:05
 * @since 0.0.1
 */
@Slf4j
public class RunnableDemo implements Runnable {


    @Override
    public void run() {
        for (int i = 4; i > 0; i--) {
            log.info(" " + i);
            // 让线程睡眠一会
            ThreadUtil.sleep(500);
        }
        log.info("exiting.");
    }
}
