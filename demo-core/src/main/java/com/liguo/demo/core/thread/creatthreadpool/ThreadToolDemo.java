package com.liguo.demo.core.thread.creatthreadpool;

import com.liguo.demo.core.factory.DemoThreadFactory;
import com.liguo.demo.core.thread.creatthread.RunnableDemo;
import com.liguo.demo.core.thread.creatthread.ThreadUtil;
import com.liguo.demo.core.util.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/2 22:35
 * @since 0.0.1
 */
@Slf4j
public class ThreadToolDemo {

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
    private final static long keepAliveTime = 1L;
    private final static TimeUnit timeUnit = TimeUnit.SECONDS;
    private final static int maxQueueNum = 200;
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
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            timeUnit,
            new LinkedBlockingQueue<>(maxQueueNum),
            new DemoThreadFactory(DEMO_THREAD_NAME),
            new ThreadPoolExecutor.CallerRunsPolicy());


    public static void main(String[] args) {
        /*cachedThreadPoolTest();
        log.info("111111");
        cachedThreadPoolTest();
        log.info("222222");*/

        submitTest();
    }

    /**
     * <p>线程池execute测试</p>
     * <p>异步,无返回结果</p>
     */
    public static void executeTest() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.execute(() -> {
                log.info("调用sayHello");
                sayHello(finalI + "");
            });
        }
        log.info("主方法结束");
    }

    public static void cachedThreadPoolTest() {
        RunnableDemo R1 = new RunnableDemo();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            cachedThreadPool.execute(R1);
        }
    }

    public static void fixedThreadPoolTest() {
        RunnableDemo R1 = new RunnableDemo();
        for (int i = 0; i < 10; i++) {
            fixedThreadPool.submit(R1);
        }
    }

    public static void scheduledThreadPoolTest() {
        RunnableDemo R1 = new RunnableDemo();
//        for (int i = 0; i < 10; i++) {
        // 表示延迟3秒执行。
//            scheduledThreadPool.schedule(R1, 3, TimeUnit.SECONDS);
//        }

        // 表示延迟1秒后每3秒执行一次。
        scheduledThreadPool.scheduleAtFixedRate(R1, 1, 10, TimeUnit.SECONDS);
    }

    public static void singleThreadExecutorTest() {
        RunnableDemo R1 = new RunnableDemo();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            singleThreadExecutor.execute(R1);
        }
    }

    private static String sayHello(String str) {
        log.info("Hello {}", str);
        ThreadUtil.sleep(1000);
        return "Hello" + str;
    }


    // *******************************************************  多线程结果归并演示  *******************************************************//

    /**
     * <p>线程池submit测试</p>
     * <p>异步,有返回结果</p>
     */
    public static void submitTest() {
        List<Future> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Future future = executorService.submit(() -> {
                log.info("调用sayHello");
                return sayHello(finalI + "");
            });
            futures.add(future);
        }

        while (true) {
            // 0.1s检查一次,根据实际情况而定
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.info("线程执行发生异常:", e);
            }
            // 已执行完线程数量
            int doneSize = 0;
            for (Future future : futures) {
                if (!future.isDone()) {
                    // 跳出内层遍历,进行下一次检查
                    break;
                }
                doneSize++;
            }
            log.info("线程总数:{},已执行完:{}!", futures.size(), doneSize);
            if (doneSize == futures.size()) {
                for (Future future : futures) {
                    try {
                        String str = (String) future.get();
                        log.info("线程返回值:{}", str);
                    } catch (Exception e) {
                        log.info(e.getMessage());
                        log.error("错误,错误堆栈:", e);
                    }
                }
                break;
            }
        }

        log.info("主方法结束");
    }

    public static String completableFutureTest() {
        long startTime = System.currentTimeMillis();
        List<CompletableFuture<String>> futureList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            // 生成推导ID
            int finalI = i;
            CompletableFuture<String> deduceTaskFuture = CompletableFuture.supplyAsync(() -> sayHello("xialiguo" + finalI), executorService).whenComplete((result, e) -> {
                if (e != null) {
                    return;
                }
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
        CompletableFuture<Void> all = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
        all.join();
        log.info("completableFutureTest结束,总耗时:{} 毫秒.", System.currentTimeMillis() - startTime);
        // 处理整个批次返回结果
        return "null";
    }

    private static void completionServiceTest() throws InterruptedException, ExecutionException {
        // 用于按照线程池线程执行顺序获取返回结果,执行完返回,非实时异步返回
        List<String> strs = new ArrayList<>();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        strs.stream().forEach(str -> completionService.submit(() -> sayHello(str)));
//        for (String str : strs) {
//            completionService.submit(() -> sayHello(str));
//        }
        // 处理线程池线程返回结果
        for (String str : strs) {
            String treeNode = completionService.take().get();
            log.info("线程返回结果:{}", treeNode);
        }
    }

    /**
     * 开启异步线程
     */
    public void beginAsyncThread() {
        new Thread(() -> asyncThread(), "thread-name").start();
    }

    private void asyncThread() {
        log.info("异步线程");
    }
}
