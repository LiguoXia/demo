package com.liguo.demo.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liguo.demo.core.dao.PlatformFlowMapper;
import com.liguo.demo.core.factory.DemoThreadFactory;
import com.liguo.demo.core.pojo.entity.PlatformFlow;
import com.liguo.demo.core.service.PlatformFlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 平台流水 服务实现类
 * </p>
 *
 * @author liguo
 * @since 2020-12-15
 */
@Slf4j
@Service
public class PlatformFlowServiceImpl extends ServiceImpl<PlatformFlowMapper, PlatformFlow> implements PlatformFlowService {
    @Autowired
    private PlatformFlowMapper platformFlowMapper;

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
    public void importDate(List<PlatformFlow> platformFlows) {
        List<PlatformFlow> platformFlowList = new ArrayList<>();
        platformFlowList.addAll(platformFlows);
        CompletableFuture.supplyAsync(() -> platformFlowMapper.batchSave(platformFlowList), executorService).whenComplete((result, e) -> {
            log.info("结果:{}", result);
            // 单个线程执行结果处理
            // 信息补充及落库
        }).exceptionally((e) -> {
            log.error("线程执行出错", e);
            return null;
        });
    }
}
