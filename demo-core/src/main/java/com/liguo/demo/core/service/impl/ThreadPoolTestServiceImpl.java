package com.liguo.demo.core.service.impl;

import com.liguo.demo.core.factory.DemoThreadFactory;
import com.liguo.demo.core.service.ThreadPoolTestService;
import com.liguo.demo.core.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程开发实例
 * <p>常用多线程并发，取结果归集的几种实现方案:</p>
 * <p>Future</p>
 * <p>FutureTask</p>
 * <p>CompletionService</p>
 * <p>CompletableFuture</p>
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
        List<CompletableFuture<String>> futureList = new ArrayList<>();

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
            futureList.add(deduceTaskFuture);
        }
        // 阻塞主线程
        ThreadPoolUtil.blockMainThread(futureList);
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

    private void threadTset1() {
        AtomicInteger successNums = new AtomicInteger(0);
        AtomicInteger failNums = new AtomicInteger(0);
        List<String> list = new ArrayList<>();
        CompletableFuture[] cfArr = list.stream().
                map(object -> CompletableFuture
                        .supplyAsync(() -> hello(object), executorService)
                        .whenComplete((result, th) -> {
                            if (th != null) {
                                // 错误统计+1
                                failNums.incrementAndGet();
                                // 异常处理
                                return;
                            }
                            // TODO 业务逻辑处理 result
                            try {
                                successNums.incrementAndGet();
                                System.out.println("hello" + result);
                            } catch (Exception e) {
                                successNums.decrementAndGet();
                                failNums.incrementAndGet();
                            }
                        })).toArray(CompletableFuture[]::new);
        // 阻塞
        CompletableFuture.allOf(cfArr).join();
    }

    private void threadTest2() throws InterruptedException, ExecutionException {
        // 用于按照线程池线程执行顺序获取返回结果,执行完返回,非实时异步返回
        List<String> strs = new ArrayList<>();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        strs.stream().forEach(str -> completionService.submit(() -> hello(str)));
//        for (String str : strs) {
//            completionService.submit(() -> hello(str));
//        }
        // 处理线程池线程返回结果
        for (String str : strs) {
            String treeNode = completionService.take().get();
            log.info("线程返回结果:{}", treeNode);
        }
    }
}
