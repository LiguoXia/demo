package com.liguo.demo.core.study.thread;

import com.liguo.demo.core.thread.creatthread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/11/30 21:33
 * @since 0.0.1
 */
@Slf4j
public class SequentialExecutionAll {
    static ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) {
        type1();
    }

    /**
     * join方法会让主线程等待
     */
    public static void type1() {
        try {
            System.out.println("main开始运行");
            Thread thread1 = new Thread(() -> {
                log.info("线程1");
            });
            thread1.start();
            // 线程的join()方法表示会调用的线程执行完才会继续执行主线程,带参数表示最多等多少时间
            thread1.join(); //让thread1强制执行完毕后，才可以执行后面的代码
            Thread thread2 = new Thread(() -> {
                log.info("线程2");
            });
            thread2.start();
            thread2.join();
            Thread thread3 = new Thread(() -> {
                log.info("线程3");
            });
            thread3.start();
            thread3.join();
            System.out.println("main运行结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void type2() {
        System.out.println("main开始运行");
        executorService.submit(new Thread(() -> {
            ThreadUtil.sleep(3000);
            log.info("线程1");
        }));
        executorService.submit(new Thread(() -> {
            log.info("线程2");
        }));
        executorService.submit(new Thread(() -> {
            log.info("线程3");
        }));
        executorService.shutdown();
        System.out.println("main运行结束");
    }

    public static void type3() {
        BlockingQueue<Thread> blockingQueue = new LinkedBlockingQueue<Thread>();
        Thread t1 = new Thread(new Thread(() -> {
            log.info("线程1");
        }));
        Thread t2 = new Thread(new Thread(() -> {
            log.info("线程2");
        }));
        Thread t3 = new Thread(new Thread(() -> {
            log.info("线程3");
        }));

        blockingQueue.add(t1);
        blockingQueue.add(t2);
        blockingQueue.add(t3);

        for (int i = 0; i < 3; i++) {
            Thread t = null;
            try {
                t = blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t.start();
            //检测线程是否还活着
            while (t.isAlive()) ;
        }
    }
}
