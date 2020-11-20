package com.liguo.demo.core.service.impl;

import com.liguo.demo.core.factory.DemoThreadFactory;
import com.liguo.demo.core.service.ThreadPoolTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/11/16 23:01
 * @since 0.0.1
 */
@Slf4j
@Service
public class ThreadPoolTestServiceImpl implements ThreadPoolTestService {
    private static final String DEMO_THREAD_NAME = "demo";
    final int cpu = Runtime.getRuntime().availableProcessors();
    final int corePoolSize = cpu + 1;
    final int maximumPoolSize = cpu * 2 + 1;
    final long keepAliveTime = 1L;
    final TimeUnit timeUnit = TimeUnit.SECONDS;
    final int maxQueueNum = 200;

    /**
     * 创建一个线程池处理事后推导
     * <p>核心线程数为CPU核数+1</p>
     * <p>最大线程数量,当workQueen满了,再创建新线程,大于maximumPoolSize会执行拒绝策略</p>
     * <p>通过DubheThreadFactory创建线程并指定线程名</p>
     * <p>拒绝策略为调用方立即执行方法</p>
     * ThreadPoolExecutor ExecutorService区别
     */
    private ThreadPoolExecutor executorService = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            timeUnit,
            new LinkedBlockingQueue<>(maxQueueNum),
            new DemoThreadFactory(DEMO_THREAD_NAME),
            new ThreadPoolExecutor.CallerRunsPolicy());


    @Override
    public String sayHello() {
        long startTime = System.currentTimeMillis();
        CompletableFuture<Void> future = new CompletableFuture<>();
        for (int i = 0; i < 400; i++) {
            // 生成推导ID
            int finalI = i;
            CompletableFuture<String> deduceTaskFuture = CompletableFuture.supplyAsync(() -> hello("xialiguo" + finalI), executorService).whenComplete((result, e) -> {
                // 单个线程执行结果处理
                String rs = result;
                log.info("返回结果：" + rs);
                // 信息补充及落库
            }).exceptionally((e) -> {
                log.error("线程执行出错", e);
                return null;
            });
            //future = CompletableFuture.allOf(deduceTaskFuture);
        }
        // 阻塞主线程
        // future.join();
        log.info("事后凭证推导结束,总笔数:{},总耗时:{} 毫秒.", "10000", System.currentTimeMillis() - startTime);
        // 处理整个批次返回结果
        return "null";
    }

    private String hello(String name) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            log.error("错误:", e);
        }
        log.info("线程池线程数:{},核心线程数:{},最大线程数:{}", executorService.getPoolSize(), executorService.getCorePoolSize(), executorService.getMaximumPoolSize());
        log.info("hello" + name);
        return "hello" + name;
    }

}
