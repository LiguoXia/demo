package com.liguo.demo.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.liguo.demo.core.pojo.entity.PlatformFlow;
import com.liguo.demo.core.pojo.entity.ThreeFlow;
import com.liguo.demo.core.service.IThreeFlowService;
import com.liguo.demo.core.service.PlatformFlowService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 经营类编制 Excel读取监听
 *
 * @author 01395755[xialiguo]
 * @version 1.2
 * @date 2020/12/14 14:29
 * @since 1.2
 */
@Slf4j
public class PlatformFlowExcelDataListener extends AnalysisEventListener<PlatformFlow> {
    /**
     * 每隔3000条存储数据库,实际使用中可以3000条,然后清理list,方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    /**
     * Excel解析对象集合
     */
    List<PlatformFlow> list = new ArrayList<>();

    private Integer currentRowIndex = 0;

    /**
     * 经营类资金计划明细表服务类
     */
    private PlatformFlowService platformFlowService;

    /**
     * 构造函数,实现类传参
     *
     * @param platformFlowService 经营类资金计划明细表 服务实现类
     */
    public PlatformFlowExcelDataListener(PlatformFlowService platformFlowService) {
        this.platformFlowService = platformFlowService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(PlatformFlow data, AnalysisContext context) {
        log.info("解析第{}条数据:{}", currentRowIndex, JSON.toJSONString(data));
        if ("国付宝".equals(data.getThirdName()) || "连连".equals(data.getThirdName()) || "智付".equals(data.getThirdName()) || "手工充值".equals(data.getThirdName())) {
            log.info("忽略");
            return;
        }
        list.add(data);
        currentRowIndex++;
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成!");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据,开始存储数据库!", list.size());
        platformFlowService.importDate(list);
        log.info("存储数据库成功!");
    }
}
