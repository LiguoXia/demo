package com.liguo.demo.core.controller;

import com.alibaba.excel.EasyExcel;
import com.liguo.demo.core.listener.Ct3ExcelDataListener;
import com.liguo.demo.core.listener.CtTxExcelDataListener;
import com.liguo.demo.core.listener.PlatformFlowExcelDataListener;
import com.liguo.demo.core.listener.ThreeFlowExcelDataListener;
import com.liguo.demo.core.pojo.entity.Ct3;
import com.liguo.demo.core.pojo.entity.CtTx;
import com.liguo.demo.core.pojo.entity.PlatformFlow;
import com.liguo.demo.core.pojo.entity.ThreeFlow;
import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.service.Ct3Service;
import com.liguo.demo.core.service.CtTxService;
import com.liguo.demo.core.service.IThreeFlowService;
import com.liguo.demo.core.service.PlatformFlowService;
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
    @Autowired
    private PlatformFlowService platformFlowService;
    @Autowired
    private Ct3Service ct3Service;
    @Autowired
    private CtTxService ctTxService;

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

    @PostMapping(value = "/upload2")
    @ApiOperation(value = "上传经营类资金计划编制", notes = "上传经营类资金计划编制")
    public Result upload2( MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), PlatformFlow.class, new PlatformFlowExcelDataListener(platformFlowService)).sheet().doRead();
        return Result.success("上传成功!");
    }

    @PostMapping(value = "/upload3")
    @ApiOperation(value = "上传经营类资金计划编制", notes = "上传经营类资金计划编制")
    public Result upload3( MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), Ct3.class, new Ct3ExcelDataListener(ct3Service)).sheet().doRead();
        return Result.success("上传成功!");
    }

    @PostMapping(value = "/upload4")
    @ApiOperation(value = "上传经营类资金计划编制", notes = "上传经营类资金计划编制")
    public Result upload4( MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), CtTx.class, new CtTxExcelDataListener(ctTxService)).sheet().doRead();
        return Result.success("上传成功!");
    }

    @PostMapping(value = "/check")
    @ApiOperation(value = "上传经营类资金计划编制", notes = "上传经营类资金计划编制")
    public Result check() throws IOException {
        ct3Service.chcck();
        return Result.success("上传成功!");
    }
}
