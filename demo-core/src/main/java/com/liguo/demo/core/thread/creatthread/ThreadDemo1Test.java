package com.liguo.demo.core.thread.creatthread;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/2 22:24
 * @since 0.0.1
 */
@Slf4j
public class ThreadDemo1Test {
    public static void main(String[] args) {
        new ThreadDemo1().start();
        for (int i = 0; i < 10; i++) {
            ThreadUtil.sleep(1000l);
            log.info("ThreadDemo1Test main 方法");
        }
    }
}
