package com.liguo.demo.core.entity;

import java.io.Serializable;
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
@EqualsAndHashCode(callSuper = false)
public class TPlatformFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方名称
     */
    private String thirdName;

    /**
     * 交易时间
     */
    private String tranTime;

    /**
     * 账户
     */
    private String account;

    /**
     * 支付号
     */
    private String payNo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 交易日期
     */
    private String tranDate;

    /**
     * 支付网关编号
     */
    private String getwatNo;

    /**
     * 金额
     */
    private String amount;

    /**
     * 完成标志
     */
    private String doneFlag;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 充值类型
     */
    private String type;


}
