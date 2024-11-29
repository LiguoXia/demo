package com.liguo.demo.core.study.锁;

import com.liguo.demo.core.thread.creatthread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/17 17:03
 * @since 0.0.1
 */
@Slf4j
public class LockMethod {
    public static Object lock = new Object();
    public synchronized void lockMethod(String a) {
        ThreadUtil.sleep(2000);
        log.info("lockMethod:{}", a);
    }

    public void unLockMethod(String a) {
        log.info("unLockMethod:{}", a);
    }

    public synchronized void lockMethod1(String a) {
        log.info("lockMethod1:{}", a);
    }

    public void lockMethod2(String a) {
        synchronized (lock) {
            ThreadUtil.sleep(2000);
            log.info("lockMethod2:{}", a);
        }
    }

    public void lockMethod3(String a) {
        synchronized (lock) {
            log.info("lockMethod3:{}", a);
        }
    }
}
