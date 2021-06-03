package com.liguo.demo.core.pojo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 平台流水
 * </p>
 *
 * @author liguo
 * @since 2020-12-15
 */
@Data
@TableName("t_ct_xt")
@EqualsAndHashCode(callSuper = false)
public class CtTx implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方名称
     */
    @ExcelProperty(index = 0)
    private String xuhao;

    /**
     * 交易时间
     */
    @ExcelProperty(index = 1)
    private String heduiqingk;

    /**
     * 账户
     */
    @ExcelProperty(index = 2)
    private String dfkhh;

    /**
     * 支付号
     */
    @ExcelProperty(index = 3)
    private String jine;


    /**
     * 交易日期
     */
    @ExcelProperty(index = 4)
    private String jiaoyirq;
}
