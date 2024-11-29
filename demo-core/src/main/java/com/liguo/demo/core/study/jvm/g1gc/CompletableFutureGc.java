package com.liguo.demo.core.study.jvm.g1gc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.liguo.demo.core.factory.DemoThreadFactory;
import com.liguo.demo.core.thread.creatthread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CompletableFuture GC测试
 * jmap -heap 172
 * jcmd 172 GC.run
 * jstat -gc 172 2000
 * jmap -histo:live 172 | head -n 30
 *
 * CallerRunsPolicy - 当触发拒绝策略，只要线程池没有关闭的话，则使用调用线程直接运行任务。一般并发比较小，性能要求不高，不允许失败。但是，由于调用者自己运行任务，如果任务提交速度过快，可能导致程序阻塞，性能效率上必然的损失较大
 * AbortPolicy - 丢弃任务，并抛出拒绝执行 RejectedExecutionException 异常信息。线程池默认的拒绝策略。必须处理好抛出的异常，否则会打断当前的执行流程，影响后续的任务执行。
 * DiscardPolicy - 直接丢弃，其他啥都没有
 * DiscardOldestPolicy - 当触发拒绝策略，只要线程池没有关闭的话，丢弃阻塞队列 workQueue 中最老的一个任务，并将新任务加入
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/9/4 10:19
 * @since 0.0.1
 */
@Slf4j
@Component
public class CompletableFutureGc {

    private static final String DEMO_THREAD_NAME = "demo";
    private final static int cpu = Runtime.getRuntime().availableProcessors();
    private final static int corePoolSize = 8;
    private final static int maximumPoolSize = 8;
    private final static long keepAliveTime = 60L;
    private final static TimeUnit timeUnit = TimeUnit.SECONDS;
    private final static int maxQueueNum = 200;

    private static final ThreadPoolExecutor executorService = new ThreadPoolExecutor(
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            timeUnit,
            new LinkedBlockingQueue<>(10),
            new DemoThreadFactory(DEMO_THREAD_NAME),
            new ThreadPoolExecutor.CallerRunsPolicy()); // CallerRunsPolicy
    // AbortPolicy 会导致程序卡死

    public static void main(String[] args) {
        ThreadUtil.sleep(20 * 1000);
        AtomicInteger dealTotalCounts = new AtomicInteger(0);
        AtomicInteger dealSuccessCounts = new AtomicInteger(0);
        AtomicInteger dealFailureCounts = new AtomicInteger(0);

        for (int i = 1; i <= 31; i++) {
            log.info("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii：{}", i);
            // 分页统计
            for (int j = 1; j <= 1; j++) {
                List<CompletableFuture<GcTestResult>> futureList = new ArrayList<>();
                // 开始条数
                List<GcTestData> outsideCashflowVOS = queryByPage(i);
                if (CollectionUtil.isEmpty(outsideCashflowVOS)) {
                    break;
                }
                for (GcTestData outsideCashflowVO : outsideCashflowVOS) {
                    CompletableFuture<GcTestResult> deduceTaskFuture = CompletableFuture.supplyAsync(() -> consume(outsideCashflowVO), executorService).whenComplete((result, e) -> {
                        if (e != null) {
                            return;
                        }
                        // 单个线程执行结果处理
                        GcTestResult rs = result;
                        log.info("消耗返回结果:{}", JSON.toJSONString(result));
                        dealTotalCounts.addAndGet(1);
                        dealSuccessCounts.addAndGet(result.getSuccess() ? 1 : 0);
                        dealFailureCounts.addAndGet(result.getSuccess() ? 0 : 1);
                        log.info("返回结果：" + JSON.toJSONString(rs));
                        // 信息补充及落库
                    }).exceptionally((e) -> {
                        log.error("消耗业务异常, 异常信息:", outsideCashflowVO.getLesseeId(), outsideCashflowVO.getSysSource(), outsideCashflowVO.getBusinessDoc(), outsideCashflowVO.getBusinessDocItem(), e);
                        return null;
                    });
                    futureList.add(deduceTaskFuture);
                }
                // 由于循环在此阻塞，内存中会常驻对象GcTestResult 实例数1000, 1000个任务执行完成,循环结束,执行下一个循环,此1000个对象会被GC回收掉
                // 阻塞主线程
                CompletableFuture<Void> all = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()]));
                all.join();
            }
        }
        // 如果在此阻塞,上面循环体中执行完后，使用中的对象就会被释放掉
        log.info("flag 1111111111");
    }

    private static List<GcTestData> queryByPage(int i) {
        List<GcTestData> gcTestDataList = new ArrayList<>();
        for (int j = 0; j < 1000; j++) {
            GcTestData data = new GcTestData();
            data.setBusinessDoc("aaaaaa:" + i);
            gcTestDataList.add(data);
        }
        return gcTestDataList;
    }

    public static GcTestResult consume(GcTestData gcTestData) {
        int i = RandomUtil.randomInt(10,11);
        log.info("随机休眠:{}秒", i);
        ThreadUtil.sleep(i * 1000);
        GcTestResult result = new GcTestResult();
        result.setBid(gcTestData.getBusinessDoc());
        result.setSuccess(true);
        return result;
    }

}
