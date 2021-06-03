package com.liguo.demo.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liguo.demo.core.dao.Ct3Mapper;
import com.liguo.demo.core.dao.CtTxMapper;
import com.liguo.demo.core.factory.DemoThreadFactory;
import com.liguo.demo.core.pojo.entity.Ct3;
import com.liguo.demo.core.pojo.entity.CtTx;
import com.liguo.demo.core.service.Ct3Service;
import com.liguo.demo.core.service.CtTxService;
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
public class CtTxServiceImpl extends ServiceImpl<CtTxMapper, CtTx> implements CtTxService {
    @Autowired
    private CtTxMapper ctTxMapper;

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
    public void importDate(List<CtTx> ctTxs) {
        List<CtTx> ctTxs1 = new ArrayList<>();
        ctTxs1.addAll(ctTxs);
        CompletableFuture.supplyAsync(() -> ctTxMapper.batchSave(ctTxs1), executorService).whenComplete((result, e) -> {
            log.info("结果:{}", result);
            // 单个线程执行结果处理
            // 信息补充及落库
        }).exceptionally((e) -> {
            log.error("线程执行出错", e);
            return null;
        });
    }

    @Override
    public List<CtTx> getBy(String jiaoyirq, String jine, String dfkhh) {
        LambdaQueryChainWrapper<CtTx> queryChainWrapper = this.lambdaQuery();
        queryChainWrapper.eq(CtTx::getJiaoyirq, jiaoyirq)
                .eq(CtTx::getJine, jine)
                .eq(CtTx::getDfkhh, dfkhh)
                .isNull(CtTx::getHeduiqingk);
        return queryChainWrapper.list();
    }

    @Override
    public void updateByXuhao(String xuhao, String otherXuhao) {
         //只更新一个属性，把名字为rhb的用户年龄更新为18，其他属性不变
        UpdateWrapper<CtTx> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("xuhao",xuhao).set("heduiqingk", otherXuhao);
        ctTxMapper.update(null, updateWrapper);
    }
}
