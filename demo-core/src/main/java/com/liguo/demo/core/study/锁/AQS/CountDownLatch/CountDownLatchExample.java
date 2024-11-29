package com.liguo.demo.core.study.锁.AQS.CountDownLatch;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * 以下示例展示了CountDownLatch的典型用法：
 * <p>
 * 主线程启动多个子线程执行任务。
 * 每个子线程执行完任务后，调用countDown()方法让计数器减一。
 * 主线程调用await()方法，等待计数器归零后继续执行。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/2 12:54
 * @since 0.0.1
 */
@Slf4j
public class CountDownLatchExample {
    public static void main(String[] args) throws InterruptedException {
        int numberOfWorkers = 3; // 定义任务线程数量
        CountDownLatch latch = new CountDownLatch(numberOfWorkers);

        // 创建并启动任务线程
        for (int i = 1; i <= numberOfWorkers; i++) {
            new Thread(new Worker(i, latch), "my-thread-" + i).start();
        }

        log.info("Main thread is waiting for workers to finish...");

        // 主线程等待所有任务线程完成
        latch.await();

        log.info("All workers finished. Main thread proceeds.");
    }
}

// 定义任务类
@Slf4j
class Worker implements Runnable {
    private final int workerId;
    private final CountDownLatch latch;

    public Worker(int workerId, CountDownLatch latch) {
        this.workerId = workerId;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            log.info("Worker " + workerId + " is doing its job.");
            Thread.sleep((long) (Math.random() * 3000)); // 模拟任务执行时间
            log.info("Worker " + workerId + " has finished.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            latch.countDown(); // 减少计数
        }
    }
}
