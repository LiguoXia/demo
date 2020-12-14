package com.liguo.demo.core.pojo.entity;

import java.io.Serializable;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资金计划系统参数表
 * </p>
 *
 * @author liguo
 * @since 2020-12-14
 */
@Data
@TableName("t_three_flow")
@EqualsAndHashCode(callSuper = false)
public class ThreeFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 结果
     */
    @ExcelProperty(index = 0)
    private String result;

    /**
     * 平台名
     */
    @ExcelProperty(index = 1)
    private String platformName;

    /**
     * 客户姓名
     */
    @ExcelProperty(index = 2)
    private String customName;

    /**
     * 身份证号
     */
    @ExcelProperty(index = 3)
    private String idCard;

    /**
     * 存管子账号
     */
    @ExcelProperty(index = 4)
    private String subAccount;

    /**
     * 手机号
     */
    @ExcelProperty(index = 5)
    private String phoneNumber;

    /**
     * 交易日期
     */
    @ExcelProperty(index = 6)
    private String tranDate;

    /**
     * 交易时间
     */
    @ExcelProperty(index = 7)
    private String tranTime;

    /**
     * 当地交易日
     */
    @ExcelProperty(index = 8)
    private String localTranDate;

    /**
     * 交易金额
     */
    @ExcelProperty(index = 9)
    private String money;

    /**
     * 收入
     */
    @ExcelProperty(index = 10)
    private String income;

    /**
     * 支出
     */
    @ExcelProperty(index = 11)
    private String expenditure;

    /**
     * 余额
     */
    @ExcelProperty(index = 12)
    private String balance;

    /**
     * 交易类型
     */
    @ExcelProperty(index = 13)
    private String tranType;

    /**
     * 交易说明
     */
    @ExcelProperty(index = 14)
    private String tranDesc;

    /**
     * 对方账号
     */
    @ExcelProperty(index = 15)
    private String otherAccounts;

    /**
     * 对方名字
     */
    @ExcelProperty(index = 16)
    private String otherName;

    /**
     * 对方证件号
     */
    @ExcelProperty(index = 17)
    private String otherIdCard;

    /**
     * 对方手机号
     */
    @ExcelProperty(index = 18)
    private String otherPhoneNumber;

    /**
     * 冲正标志
     */
    @ExcelProperty(index = 19)
    private String flag;


}
