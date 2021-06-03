package com.liguo.demo.core.thread.creatthread;

import lombok.extern.slf4j.Slf4j;

/**
 * 线程测试方法
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/2 22:08
 * @since 0.0.1
 */
@Slf4j
public class RunnableDemoTest {
    public static void main(String args[]) throws Exception{
        RunnableDemo R1 = new RunnableDemo();
        Thread thread1 = new Thread(R1);
        thread1.start();
        log.info("121212");
    }
}
