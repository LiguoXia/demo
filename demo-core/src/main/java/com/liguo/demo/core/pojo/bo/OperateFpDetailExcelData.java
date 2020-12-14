package com.liguo.demo.core.pojo.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OperateFpDetailExcelData {

    @NotBlank(message = "流动性项目不能为空!")
    @ExcelProperty(index = 1)
    @ApiModelProperty(value = "流动性项目")
    private String fpFlowProject;

    @NotBlank(message = "资金计划金额不能为空!")
    @ExcelProperty(index = 2)
    @ApiModelProperty(value = "资金计划金额")
    private String fpAmountCent;
}
