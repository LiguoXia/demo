package com.liguo.demo.core.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资金计划消耗信息表
 * </p>
 *
 * @author liguo
 * @since 2022-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TConsumeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资金计划周期（编号）
     */
    private String fpNo;

    /**
     * 推导编号
     */
    private Long deductionNo;

    /**
     * 占用消耗Id
     */
    private String occupyConsumeId;

    /**
     * 支付建议号
     */
    private String payTradeId;

    /**
     * 流量项目Id
     */
    private String fpFlowId;

    /**
     * 业务类型
     */
    private Integer bizType;

    /**
     * 资金计划凭证号
     */
    private String fpVoucherNo;

    /**
     * 资金计划凭证行项目
     */
    private String fpVoucherRowNo;

    /**
     * 被冲销资金计划凭证号
     */
    private String cancelFpVoucherNo;

    /**
     * 被冲销资金计划行项目
     */
    private String cancelFpVoucherRowNo;

    /**
     * 系统编码
     */
    private String sysSource;

    /**
     * 资金计划范围
     */
    private String fpRange;

    /**
     * 资金计划组织
     */
    private String fpOrgCode;

    /**
     * 资金计划周期类型
     */
    private String fpCycleType;

    /**
     * 资金计划业务类型
     */
    private String fpBizType;

    /**
     * 流动性项目
     */
    private String fpFlowProject;

    /**
     * 规则id
     */
    private String fpFlowRuleId;

    /**
     * 项目类别
     */
    private String projectType;

    /**
     * 项目代码
     */
    private String projectCode;

    /**
     * 控制级别
     */
    private String fpControlLevel;

    /**
     * 是否总值控制
     */
    private String fpGross;

    /**
     * 公司代码
     */
    private String orgVoucherCorpCode;

    /**
     * 源会计年度
     */
    private String orgVoucherAcctingYear;

    /**
     * 源凭证会计日期
     */
    private LocalDate orgVoucherAcctingDate;

    /**
     * 起始会计凭证编号
     */
    private String orgVoucherNo;

    /**
     * 起始会计凭证行项目号
     */
    private String orgVoucherRowNo;

    /**
     * 目标公司代码
     */
    private String corpCode;

    /**
     * 会计年度
     */
    private String acctingYear;

    /**
     * 凭证会计日期
     */
    private LocalDate acctingDate;

    /**
     * 过账期间
     */
    private LocalDate postingDate;

    /**
     * 目标会计凭证编号
     */
    private String voucherNo;

    /**
     * 目标会计凭证行项目号
     */
    private String voucherRowNo;

    /**
     * 目标凭证行项目文本
     */
    private String voucherRowProjectTxt;

    /**
     * 源凭证行项目文本
     */
    private String orgVoucherRowProjectTxt;

    /**
     * 事前事后推导标识：01事前推导 02事后推导
     */
    private String beforeOrBehind;

    /**
     * 数据来源 1-源数据;2-派生数据
     */
    private String dataSource;

    /**
     * 外围系统业务标识
     */
    private String outerGuid;

    /**
     * 银行科目
     */
    private String bankSubject;

    /**
     * 供应商凭证号
     */
    private String applyVoucherNo;

    /**
     * 供应商凭证行号
     */
    private String applyVoucherRowNo;

    /**
     * 专用流量项
     */
    private String specialFlowProject;

    /**
     * 利润中心
     */
    private String profitCenterNo;

    /**
     * 流量项总账科目
     */
    private String ledgerSubject;

    /**
     * 贸易伙伴
     */
    private String tradePartner;

    /**
     * 借/贷方
     */
    private String debitCreditFlag;

    /**
     * 统收统付标识
     */
    private String sendOrRecvFlag;

    /**
     * 资金计划货币
     */
    private String fpCurrency;

    /**
     * 资金计划金额
     */
    private Long fpAmountCent;

    /**
     * 交易货币
     */
    private String currency;

    /**
     * 交易金额
     */
    private Long payAmountCent;

    /**
     * 本位币
     */
    private String standardCurrency;

    /**
     * 本位币金额
     */
    private Long standardAmountCent;

    /**
     * 当前状态
     */
    private String status;

    /**
     * 操作标识
     */
    private String operate;

    /**
     * 冲销标识
     */
    private String cancelFlag;

    /**
     * 冻结标识
     */
    private String frozenFlag;

    /**
     * 推导方式
     */
    private String deduceMethod;

    /**
     * 支付方式，01:自动支付 02:手动支付
     */
    private String payMethod;

    /**
     * 支付申请类型0-普通、1-紧急、2-空
     */
    private String payType;

    /**
     * 创建日期
     */
    private LocalDate createDate;

    /**
     * 创建时间
     */
    private LocalTime createPointTime;

    /**
     * 消息类型
     */
    private String msgType;

    /**
     * 消息号
     */
    private String msgCode;

    /**
     * 消息文本
     */
    private String msg;

    /**
     * 已同步版本号
     */
    private Integer syncVersion;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 租户id
     */
    private Long lesseeId;

    /**
     * 创建时间 yyyy-MM-dd hh:mm:ss
     */
    private LocalDateTime createTime;

    /**
     * 修改时间 yyyy-MM-dd hh:mm:ss
     */
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 修改人
     */
    private String modifiedBy;

    /**
     * 删除标识 0:未删除,其余:已删除
     */
    private Long delFlag;

    /**
     * 备注
     */
    private String remark;


}
