package com.liguo.demo.core.test.jmater;

import com.alibaba.fastjson.JSON;
import com.liguo.demo.core.factory.DemoThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.rmi.server.UID;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * TODO WHAT
 * <P></P>
 *
 * @author 01395755[xialiguo]
 * @version 4.4
 * @date 2022/3/22 15:14
 * @since 4.4
 */
@Slf4j
@Service
public class DeduceTryServiceImpl implements IDeduceTryService {

    ExecutorService deduceTryThreadPool = Executors.newFixedThreadPool(10, new DemoThreadFactory("deduce_try"));

    /**
     * <p>创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程</p>
     * <p>线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。</p>
     */
    private final static ExecutorService cachedThreadPool = Executors.newCachedThreadPool(new DemoThreadFactory("ThreadToolDemo"));

    /**
     * <p>创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待</p>
     */
    private final static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3, new DemoThreadFactory("ThreadToolDemo"));

    /**
     * <p>ScheduledExecutorService继承ExecutorService<p/>
     * <p>创建一个定长线程池，支持定时及周期性任务执行。延迟执行</p>
     */
    private final static ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3, new DemoThreadFactory("ThreadToolDemo"));

    /**
     * <p>创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行</p>
     */
    private final static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor(new DemoThreadFactory("ThreadToolDemo"));


    private static final String DEMO_THREAD_NAME = "demo";
    private final static int cpu = Runtime.getRuntime().availableProcessors();
    private final static int corePoolSize = cpu + 1;
    private final static int maximumPoolSize = cpu * 2 + 1;
    private final static long keepAliveTime = 10L;
    private final static TimeUnit timeUnit = TimeUnit.SECONDS;
    private final static int maxQueueNum = 20;
    /**
     * 创建一个线程池处理事后推导
     * <p>核心线程数为CPU核数+1</p>
     * <p>最大线程数量,当workQueen满了,再创建新线程,大于maximumPoolSize会执行拒绝策略</p>
     * <p>通过DubheThreadFactory创建线程并指定线程名</p>
     * <p>拒绝策略为调用方立即执行方法</p>
     * ThreadPoolExecutor ExecutorService区别
     * <p>
     * 知识点：
     * 1.LinkedBlockingQueue 不设置大小为无界队列，线程会一直是核心线程数，若设置大小，则同ArrayBlockingQueue
     * 2.ArrayBlockingQueue为有界队列,且必须设置大小，若线程池任务大于核心线程数，则添加到任务队列，若任务队列满了则开启新线程，直到达到最大
     * 线程数，这些非核心线程存活时间由keepAliveTime和timeUnit控制，若希望复用，则稍微调大
     */
    private static final ThreadPoolExecutor executorService = new ThreadPoolExecutor(
            10,
            20,
            keepAliveTime,
            timeUnit,
            new LinkedBlockingQueue<>(maxQueueNum),
            new DemoThreadFactory(DEMO_THREAD_NAME),
            new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 推导试跑
     *
     * @param req 请求对象
     * @return 返回对象
     */
    @Override
    public DeduceTryResp deduceTry(DeduceTryReq req) throws ExecutionException, InterruptedException {
        // 1 参数校验
        log.info("deduceTry 参数{}", JSON.toJSONString(req));
        // 2
        // 推导
        DeduceTryResp resp = new DeduceTryResp();
        long deductionNo = UUID.randomUUID().variant();
        resp.setDeductionNo(deductionNo);
        long startTime = System.currentTimeMillis();
        CompletionService<DeduceTryResp> completionService = new ExecutorCompletionService<>(deduceTryThreadPool);
        Future<DeduceTryResp> future = completionService.submit(() -> runDeduceTry(req));

        while (true) {
            if (future.isDone()) {
                resp.setDeduceStatus(2);
                log.info("deduceTry 推导执行完,返回结果");
                return resp;
            }
            if (System.currentTimeMillis() - startTime > 30000) {
                log.info("deduceTry 推导试跑30秒还未执行完,先返回结果,后台继续运行推导");
                resp.setDeduceStatus(3);
                return resp;
            }
            // 休眠1秒
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                this.log.info("线程执行发生异常:", e);
            }
        }
    }

    private DeduceTryResp runDeduceTry(DeduceTryReq req) {
        log.info("开始推导");
        int max = 50;
        int min = 1;
        long random = (long) (min + Math.random() * (max - min + 1));
        // 休眠1秒
        log.info("休眠:{}秒", random);
        try {
            Thread.sleep(random * 1000);
        } catch (InterruptedException e) {
            this.log.info("线程执行发生异常:", e);
        }

        log.info("休眠:{}秒结束", random);
        log.info("推导结束");
        return null;
    }
}

