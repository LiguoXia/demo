package com.liguo.demo.core.thread.creatthread;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/3 20:58
 * @since 0.0.1
 */
@Slf4j
public class ThreadUtil {
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log.error("线程休眠异常");
        }
    }
}
