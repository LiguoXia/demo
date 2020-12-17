package com.liguo.demo.core.pojo.entity;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 平台流水
 * </p>
 *
 * @author liguo
 * @since 2020-12-15
 */
@Data
@TableName("t_platform_flow")
@EqualsAndHashCode(callSuper = false)
public class PlatformFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方名称
     */
    @ExcelProperty(index = 0)
    private String thirdName;

    /**
     * 交易时间
     */
    @ExcelProperty(index = 1)
    private String tranTime;

    /**
     * 账户
     */
    @ExcelProperty(index = 2)
    private String account;

    /**
     * 支付号
     */
    @ExcelProperty(index = 3)
    private String payNo;

    /**
     * 姓名
     */
    @ExcelProperty(index = 4)
    private String customName;

    /**
     * 身份证号
     */
    @ExcelProperty(index = 5)
    private String idCard;

    /**
     * 交易日期
     */
    @ExcelProperty(index = 6)
    private String tranDate;

    /**
     * 支付网关编号
     */
    @ExcelProperty(index = 7)
    private String getwatNo;

    /**
     * 金额
     */
    @ExcelProperty(index = 8)
    private String amount;

    /**
     * 完成标志
     */
    @ExcelProperty(index = 9)
    private String doneFlag;

    /**
     * 订单ID
     */
    @ExcelProperty(index = 10)
    private String orderId;

    /**
     * 充值类型
     */
    @ExcelProperty(index =11)
    private String tranType;


}
