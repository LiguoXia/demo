package com.liguo.demo.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liguo.demo.core.config.BizException;
import com.liguo.demo.core.pojo.bo.OperateFpDetailExcelData;
import com.liguo.demo.core.util.BeanValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
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
public class OperateFpDetailExcelDataListener extends AnalysisEventListener<OperateFpDetailExcelData> {
    /**
     * 每隔3000条存储数据库,实际使用中可以3000条,然后清理list,方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    /**
     * Excel解析对象集合
     */
    List<OperateFpDetailExcelData> list = new ArrayList<>();

    /**
     * 读取经营类资金计划编制模板需要忽略的行索引,Excel第二行对应index 0
     */
    private static final List<Integer> IGNORE_ROW = Arrays.asList(1, 2, 12, 30, 42, 57, 63, 72, 73, 75, 78, 81, 84, 87, 88);

    private Integer currentRowIndex = 0;

    /**
     * 经营类资金计划明细表服务类
     */
    private IService service;

    /**
     * 构造函数,实现类传参
     *
     * @param iService 经营类资金计划明细表 服务实现类
     */
    public OperateFpDetailExcelDataListener(IService iService) {
        this.service = iService;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(OperateFpDetailExcelData data, AnalysisContext context) {
        log.info("解析第{}条数据:{}", currentRowIndex, JSON.toJSONString(data));
        if (IGNORE_ROW.contains(currentRowIndex++)) {
            log.info("当前行数据为标题行,忽略!");
            return;
        }
        String validateMsg = BeanValidateUtil.validateReturnMsg(data);
        if (StringUtils.isNoneBlank(validateMsg)) {
            throw new BizException(500, "第" + (currentRowIndex + 1) + "行数据错误,错误信息:[" + validateMsg + "]");
        }
        list.add(data);
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
        // Excel行数校验
        if (currentRowIndex != 97) {
            throw new BizException(500, "经营类资金计划编制模板错误,请使用下载模板编制,不要修改格式!");
        }
        saveData();
        log.info("所有数据解析完成!");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        log.info("{}条数据,开始存储数据库!", list.size());
        // iOperateFpDetailService.importDate(fpId, list);
        log.info("存储数据库成功!");
    }
}
