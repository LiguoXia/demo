package com.liguo.demo.core.thread.creatthread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * 实现Callable接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/3 21:14
 * @since 0.0.1
 */
@Slf4j
public class CallableDemo implements Callable<String> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public String call() throws Exception {
        for (int i = 4; i > 0; i--) {
            log.info(" " + i);
            // 让线程睡眠一会
            ThreadUtil.sleep(500);
        }
        if (true) {
            throw new Exception("callable 线程出现异常");
        }
        log.info("exiting.");
        return "Success";
    }
}
