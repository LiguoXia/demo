package com.liguo.demo.core.study.锁.AQS.condition;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/1 17:45
 * @since 0.0.1
 */
public class ProducerConsumerMainTest {
    public static void main(String[] args) {
        ProducerConsumer pc = new ProducerConsumer();

        // 创建生产者线程
        Thread producerThread = new Thread(() -> {
            try {
                pc.produce(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Producer was interrupted.");
            }
        });

        // 创建消费者线程
        Thread consumerThread = new Thread(() -> {
            try {
                pc.consume();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Consumer was interrupted.");
            }
        });

        // 启动生产者和消费者线程
        producerThread.start();
        consumerThread.start();

        // 主线程等待生产者和消费者线程完成
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted.");
        }
    }
}
