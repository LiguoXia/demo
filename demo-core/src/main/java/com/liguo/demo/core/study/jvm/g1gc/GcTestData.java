package com.liguo.demo.core.study.jvm.g1gc;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/9/4 10:20
 * @since 0.0.1
 */
@Data
public class GcTestData {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "系统编码")
    private String sysSource;

    @ApiModelProperty(value = "业务凭证号原表id(唯一)")
    private String businessDoc;

    @ApiModelProperty(value = "业务凭证行")
    private String businessDocItem;

    @ApiModelProperty(value = "费用类型")
    private String expenseType;

    @ApiModelProperty(value = "源公司代码")
    private String orgVoucherCorpCode;

    @JSONField(format = "yyyy-MM-dd")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "源凭证会计日期yyyy-MM-dd")
    private Date orgVoucherAcctingDate;

    @ApiModelProperty(value = "目标公司代码")
    private String corpCode;

    @ApiModelProperty(value = "目标凭证行项目文本")
    private String voucherRowProjectTxt;

    @ApiModelProperty(value = "利润中心")
    private String profitCenterNo;

    @ApiModelProperty(value = "项目代码")
    private String projectCode;

    @ApiModelProperty(value = "贸易伙伴")
    private String tradePartner;

    @ApiModelProperty(value = "借/贷方")
    private String debitCreditFlag;

    @ApiModelProperty(value = "统收统付标识")
    private String sendOrRecvFlag;

    @ApiModelProperty(value = "流动性项目")
    private String fpFlowProject;

    @ApiModelProperty(value = "交易货币")
    private String currency;

    @ApiModelProperty(value = "交易金额")
    private Long payAmountCent;

    @ApiModelProperty(value = "本位币")
    private String standardCurrency;

    @ApiModelProperty(value = "本位币金额")
    private Long standardAmountCent;

    @ApiModelProperty(value = "汇率")
    private BigDecimal exchangeRate;

    @ApiModelProperty(value = "接收消息")
    private String recieveMsg;

    @ApiModelProperty(value = "现金流凭证号")
    private String fpVoucherNo;

    @ApiModelProperty(value = "现金流凭证行")
    private String fpVoucherRowNo;

    @ApiModelProperty(value = "冲销现金流凭证号")
    private String cancelFpVoucherNo;

    @ApiModelProperty(value = "冲销现金流凭证行")
    private String cancelFpVoucherRowNo;

    @ApiModelProperty(value = "源表时间戳 yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date srcTimestamp;

    @ApiModelProperty(value = "处理状态;0-初始, 1-消耗成功, 2-消耗失败, 3-冲销成功, 4-冲销失败")
    private Integer dealStatus;

    @ApiModelProperty(value = "占用消耗调用次数")
    private Integer ocTransferCount;

    @ApiModelProperty(value = "冲销调用次数")
    private Integer reverseTransferCount;

    @ApiModelProperty(value = "处理消息")
    private String dealMsg;

    @ApiModelProperty(value = "处理时间 yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date dealTime;

    @ApiModelProperty(value = "租户id")
    private Long lesseeId;

    @ApiModelProperty(value = "创建时间 yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @ApiModelProperty(value = "修改时间 yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "修改人")
    private String modifiedBy;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "删除标识 非0:删除 0：未删除")
    private Long delFlag;
}
