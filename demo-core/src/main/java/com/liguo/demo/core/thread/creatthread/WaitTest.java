package com.liguo.demo.core.thread.creatthread;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/3 22:50
 * @since 0.0.1
 */
@Slf4j
public class WaitTest {
    public static void main(String[] args) {
        ThreadA t1 = new ThreadA("t1");
        synchronized (t1) {
            try {
                // 启动“线程t1”
                log.info("start t1");
                t1.start();
                // 主线程等待t1通过notify()唤醒。
                log.info(" wait()");
                t1.wait();  //  不是使t1线程等待，而是当前执行wait的线程等待
                log.info(" continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

@Slf4j
class ThreadA extends Thread {
    public ThreadA(String name) {
        super(name);
    }

    public void run() {
        synchronized (this) {
            try {
                Thread.sleep(1000); //  使当前线阻塞 1 s，确保主程序的 t1.wait(); 执行之后再执行 notify()
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.info(Thread.currentThread().getName() + " call notify()");
            // 唤醒当前的wait线程
            this.notify();
        }
    }
}
