package com.liguo.demo.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liguo.demo.core.dao.Ct3Mapper;
import com.liguo.demo.core.dao.PlatformFlowMapper;
import com.liguo.demo.core.factory.DemoThreadFactory;
import com.liguo.demo.core.pojo.entity.Ct3;
import com.liguo.demo.core.pojo.entity.CtTx;
import com.liguo.demo.core.pojo.entity.PlatformFlow;
import com.liguo.demo.core.pojo.entity.TParamConfig;
import com.liguo.demo.core.service.Ct3Service;
import com.liguo.demo.core.service.CtTxService;
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
public class Ct3ServiceImpl extends ServiceImpl<Ct3Mapper, Ct3> implements Ct3Service {
    @Autowired
    private Ct3Mapper ct3Mapper;
    @Autowired
    private CtTxService ctTxService;

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
    public void importDate(List<Ct3> ct3s) {
        List<Ct3> ct3s1 = new ArrayList<>();
        ct3s1.addAll(ct3s);
        CompletableFuture.supplyAsync(() -> ct3Mapper.batchSave(ct3s1), executorService).whenComplete((result, e) -> {
            log.info("结果:{}", result);
            // 单个线程执行结果处理
            // 信息补充及落库
        }).exceptionally((e) -> {
            log.error("线程执行出错", e);
            return null;
        });
    }

    @Override
    public List<Ct3> getAll() {
        LambdaQueryChainWrapper<Ct3> queryChainWrapper = this.lambdaQuery();
        return queryChainWrapper.list();
    }

    @Override
    public void updateByXuhao(String xuhao, String otherXuhao) {
        //只更新一个属性，把名字为rhb的用户年龄更新为18，其他属性不变
        UpdateWrapper<Ct3> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("xuhao",xuhao).set("heduiqingk", otherXuhao);
        ct3Mapper.update(null, updateWrapper);
    }

    @Override
    public void chcck() {
        List<Ct3> ct3s = getAll();
        for (Ct3 ct3: ct3s) {
            List<CtTx> ctTxes = ctTxService.getBy(ct3.getJiaoyirq(), ct3.getJine(), ct3.getDuifangzhm());
            if (CollectionUtils.isEmpty(ctTxes)) {
                continue;
            } else {
                log.info("匹配成功");
                log.info(ctTxes.toString());
                updateByXuhao(ct3.getXuhao(), ctTxes.get(0).getXuhao());
                ctTxService.updateByXuhao(ctTxes.get(0).getXuhao(),ct3.getXuhao());
            }
        }
    }
}
