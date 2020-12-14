package com.liguo.demo.core.controller;

import com.alibaba.excel.EasyExcel;
import com.liguo.demo.core.listener.ThreeFlowExcelDataListener;
import com.liguo.demo.core.pojo.entity.ThreeFlow;
import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.service.IThreeFlowService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/core/excel")
public class ExcelController {
    @Autowired
    private IThreeFlowService threeFlowService;

    /**
     * 文件上传
     * <p>1. 创建excel对应的实体对象
     * <p>2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ThreeFlowExcelDataListener}
     * <p>3. 直接读即可
     *
     * @param file Excel文件
     * @return
     * @throws IOException
     */
    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传经营类资金计划编制", notes = "上传经营类资金计划编制")
    public Result upload( MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), ThreeFlow.class, new ThreeFlowExcelDataListener(threeFlowService)).sheet().doRead();
        return Result.success("上传成功!");
    }
}