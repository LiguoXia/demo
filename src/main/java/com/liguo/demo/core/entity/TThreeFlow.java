package com.liguo.demo.core.entity;

import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
public class TThreeFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 结果
     */
    private String result;

    /**
     * 平台名
     */
    private String platformName;

    /**
     * 客户姓名
     */
    private String customName;

    /**
     * 身份证号
     */
    private Long idCard;

    /**
     * 存管子账号
     */
    private String subAccount;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 交易日期
     */
    private String tranDate;

    /**
     * 交易时间
     */
    private String tranTime;

    /**
     * 当地交易日
     */
    private String localTranDate;

    /**
     * 交易金额
     */
    private String money;

    /**
     * 收入
     */
    private String income;

    /**
     * 支出
     */
    private String expenditure;

    /**
     * 余额
     */
    private String balance;

    /**
     * 交易类型
     */
    private String tranType;

    /**
     * 交易说明
     */
    private String tranDesc;

    /**
     * 对方账号
     */
    private String otherAccounts;

    /**
     * 对方名字
     */
    private String otherName;

    /**
     * 对方证件号
     */
    private String otherIdCard;

    /**
     * 对方手机号
     */
    private String otherPhoneNumber;

    /**
     * 冲正标志
     */
    private String flag;


}
