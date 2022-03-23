package com.liguo.demo.core.test.jmater;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 推导试跑请求对象
 *
 * @author 01395755[xialiguo]
 * @version 4.1
 * @date 2022/3/22 14:47
 * @since 4.1
 */
@Data
public class DeduceTryReq {

    @ApiModelProperty(value = "租户ID")
    private Long lesseeId;

    @ApiModelProperty(value = "系统编码")
    private String sysCode;

    @ApiModelProperty(value = "公司代码")
    private String corpCode;

    @ApiModelProperty(value = "公司名称")
    private String corpName;

    @ApiModelProperty(value = "会计年度")
    private String acctingYear;

    @ApiModelProperty(value = "会计期间")
    private String acctingMonth;

    @ApiModelProperty(value = "会计凭证号")
    private String orgVoucherNo;

    @ApiModelProperty(value = "事前事后标识, 01-事前; 02-事后")
    private String beforeOrBehind;
}
