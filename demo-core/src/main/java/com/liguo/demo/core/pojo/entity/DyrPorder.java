package com.liguo.demo.core.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author liguo
 * @since 2023-02-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class DyrPorder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    private String orderNumber;

    private Integer productId;

    private Integer customerId;

    private BigDecimal totalPrice;

    private Integer createTime;

    private Integer payTime;

    /**
     * 1待付款 2已付款，待充值 3充值中 4充值成功 5充值失败  6退款成功 7取消
     */
    private Integer status;

    private String remark;

    private String mobile;

    /**
     * 归属地
     */
    private String guishu;

    /**
     * 充值内容
     */
    private String body;

    /**
     * 产品名字
     */
    private String productName;

    /**
     * 产品描述
     */
    private String productDesc;

    /**
     * 运营商
     */
    private String isp;

    private String title;

    /**
     * 产品类型
     */
    private Integer typec;

    /**
     * 充值类型1话费2流量3电费...
     */
    private Integer type;

    /**
     * 是否删除
     */
    private Integer isDel;

    private Integer apireqTime;

    /**
     * 退款时间
     */
    private Integer refundTime;

    /**
     * 订单来源1公众号 2小程序 3渠道端 4API
     */
    private Integer client;

    /**
     * 返利ID
     */
    private Integer rebateId;

    /**
     * 返利金额
     */
    private BigDecimal rebatePrice;

    /**
     * 是否返利
     */
    private Integer isRebate;

    /**
     * 是否开启自动充值
     */
    private Integer apiOpen;

    private Integer rebateTime;

    /**
     * api数组
     */
    private String apiArr;

    /**
     * 当前正在使用api序号
     */
    private Integer apiCurIndex;

    private Integer payWay;

    /**
     * 下发通知的次数
     */
    private Integer notificationNum;

    /**
     * 是否下发通知
     */
    private Integer isNotification;

    /**
     * 外部订单号
     */
    private String outTradeNum;

    /**
     * 充值完成时间，成功或者失败
     */
    private Integer finishTime;

    /**
     * 成本
     */
    private BigDecimal cost;

    /**
     * 回调地址
     */
    private String notifyUrl;

    private String weixinAppid;

    private String apiOrderNumber;

    /**
     * 回调时间
     */
    private Integer notificationTime;

    private Integer apiCurCount;

    private Integer apiCurId;

    private Integer apiCurParamId;

    /**
     * 当前api提交的次数
     */
    private Integer apiCurNum;

    private Integer apifailTime;

    /**
     * 提交接口的上游单号
     */
    private String apiTradeNum;

    /**
     * 申请退款 0未申请，1已申请
     */
    private Integer applyRefund;

    /**
     * 是否拆单0未拆 1子单 2被拆
     */
    private Integer isApart;

    /**
     * 拆单来自单号
     */
    private String apartOrderNumber;

    /**
     * 退款金额
     */
    private BigDecimal refundPrice;

    private BigDecimal h5fxpayPrice;

    private Integer rebateId2;

    private BigDecimal rebatePrice2;

    /**
     * 标签
     */
    private String lable;

    private Integer isRebate2;

    private String guishuPro;

    private String guishuCity;

    /**
     * api延迟到某个提交时间点
     */
    private Integer delayTime;

    /**
     * 已充值的面值
     */
    private BigDecimal chargeAmount;

    /**
     * 支付交易号
     */
    private String serialNumber;

    /**
     * 备用参数1
     */
    private String param1;

    /**
     * 备用参数2
     */
    private String param2;

    /**
     * ks业务类型
     */
    private Integer kuaishouBiztype;

    /**
     * 用户自己设置的标签
     */
    private String userLable;

    /**
     * 备用参数3
     */
    private String param3;

    /**
     * 反查时间
     */
    private Integer peggingTime;

    /**
     * 充值完成后返回的卡密，流水号，凭证号等
     */
    private String chargeKami;

    private String extendParam1;

    private String extendParam2;

    private String extendParam3;

    /**
     * 是否接码
     */
    private Integer isJiema;

    /**
     * 订单锁定状态
     */
    private Integer tlocking;

    private Integer smsNoticeTime;

    private Integer smsNoticeCount;

    /**
     * 申请中断
     */
    private Integer applyBreak;


}
