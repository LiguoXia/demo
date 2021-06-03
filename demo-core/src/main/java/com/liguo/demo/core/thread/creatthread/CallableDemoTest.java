package com.liguo.demo.core.thread.creatthread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * CallableDemoTest
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/3 21:15
 * @since 0.0.1
 */
@Slf4j
public class CallableDemoTest {
    public static void main(String args[]) throws Exception {
        CallableDemo callable = new CallableDemo();
        FutureTask futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        log.info("1111111111111");
        String a = null;
        try {
            a = (String) futureTask.get();
        } catch (InterruptedException e) {
            log.error("e1:{}", e.getMessage());
        } catch (ExecutionException e) {
            log.error("e2:{}", e.getMessage());
        }
        log.info("CallableDemo 返回:{}", a);
        log.info("2222222222222");
    }
}
