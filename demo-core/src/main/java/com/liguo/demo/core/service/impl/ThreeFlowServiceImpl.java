package com.liguo.demo.core.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liguo.demo.core.dao.ThreeFlowMapper;
import com.liguo.demo.core.factory.DemoThreadFactory;
import com.liguo.demo.core.pojo.entity.ThreeFlow;
import com.liguo.demo.core.service.IThreeFlowService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * 资金计划系统参数表 服务实现类
 * </p>
 *
 * @author liguo
 * @since 2020-12-14
 */
@Slf4j
@Service
public class ThreeFlowServiceImpl extends ServiceImpl<ThreeFlowMapper, ThreeFlow> implements IThreeFlowService {
    @Autowired
    private ThreeFlowMapper threeFlowMapper;

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
    public void importDate(List<ThreeFlow> threeFlowList) {
        List<ThreeFlow> threeFlows = new ArrayList<>();
        threeFlows.addAll(threeFlowList);
        CompletableFuture.supplyAsync(() -> threeFlowMapper.batchSave(threeFlows), executorService).whenComplete((result, e) -> {
            log.info("结果:{}", result);
            // 单个线程执行结果处理
            // 信息补充及落库
        }).exceptionally((e) -> {
            log.error("线程执行出错", e);
            return null;
        });
//        saveBatch(threeFlowList);
        //threeFlowMapper.batchSave(threeFlowList);
    }

    @Override
    public void delete() {
        int i = 0;
        LambdaQueryChainWrapper<ThreeFlow> wrapper = this.lambdaQuery();
        wrapper.eq(ThreeFlow::getFlag, "冲正交易");
        List<ThreeFlow> threeFlows = wrapper.list();
        for (ThreeFlow threeFlow : threeFlows) {
            LambdaQueryChainWrapper<ThreeFlow> wrapper2 = this.lambdaQuery();
            wrapper2.eq(StringUtils.isNoneBlank(threeFlow.getCustomName()), ThreeFlow::getCustomName, threeFlow.getCustomName());
            wrapper2.eq(StringUtils.isNoneBlank(threeFlow.getIdCard()), ThreeFlow::getIdCard, threeFlow.getIdCard());
            wrapper2.eq(StringUtils.isNoneBlank(threeFlow.getSubAccount()), ThreeFlow::getSubAccount, threeFlow.getSubAccount());
            wrapper2.eq(StringUtils.isNoneBlank(threeFlow.getPhoneNumber()), ThreeFlow::getPhoneNumber, threeFlow.getPhoneNumber());
            wrapper2.eq(StringUtils.isNoneBlank(threeFlow.getTranDate()), ThreeFlow::getTranDate, threeFlow.getTranDate());
            wrapper2.eq(StringUtils.isNoneBlank(threeFlow.getMoney()), ThreeFlow::getMoney, "-" + threeFlow.getMoney());
            wrapper2.eq(StringUtils.isNoneBlank(threeFlow.getTranType()), ThreeFlow::getTranType, threeFlow.getTranType());
            wrapper2.eq(StringUtils.isNoneBlank(threeFlow.getOtherAccounts()), ThreeFlow::getOtherAccounts, threeFlow.getOtherAccounts());
            wrapper2.eq(StringUtils.isNoneBlank(threeFlow.getOtherIdCard()), ThreeFlow::getOtherIdCard, threeFlow.getOtherIdCard());
            wrapper2.eq(StringUtils.isNoneBlank(threeFlow.getOtherPhoneNumber()), ThreeFlow::getOtherPhoneNumber, threeFlow.getOtherPhoneNumber());
            wrapper2.isNull(ThreeFlow::getDelFlag);
            List<ThreeFlow> threeFlows2 = wrapper2.list();
            if (threeFlows2 == null || threeFlows2.size() <= 0) {
                log.info("冲正{}没有找到原交易", threeFlow.toString());
                continue;
            }

            ThreeFlow threeFlowZ = threeFlows2.get(0);
            // 删除正交易
            ThreeFlow threeFlow1 = new ThreeFlow();
            threeFlow1.setId(threeFlowZ.getId());
            threeFlow1.setDelFlag(Long.valueOf(i));
            this.updateById(threeFlow1);
            // 删除冲正交易
            ThreeFlow threeFlow2 = new ThreeFlow();
            threeFlow2.setId(threeFlow.getId());
            threeFlow2.setDelFlag(Long.valueOf(i));
            this.updateById(threeFlow2);
            log.info("冲正：{}", threeFlow.toString());
            log.info("原来：{}", threeFlows2.get(0).toString());
            i++;
        }
    }
}
