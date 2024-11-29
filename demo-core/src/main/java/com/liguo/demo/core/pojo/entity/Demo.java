package com.liguo.demo.core.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Demo对象
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/20 16:48
 * @since 0.0.1
 */
@ApiModel(description = "Demo对象")
@Data
public class Demo implements Serializable {
    @ApiModelProperty(value = "自增ID", example = "1", required = true)
    private Long id;

    @ApiModelProperty(value = "整型列", example = "123", required = true)
    private int intColumn;

    @ApiModelProperty(value = "可变长度字符串", example = "示例字符串", required = true)
    private String varcharColumn;

    @ApiModelProperty(value = "长文本", example = "这是一个长文本内容", required = false)
    private String textColumn;

    @ApiModelProperty(value = "日期时间", example = "2024-01-01 12:00:00", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date datetimeColumn;

    @ApiModelProperty(value = "小数列", example = "12345.67", required = true)
    private BigDecimal decimalColumn;

    @ApiModelProperty(value = "单精度浮点数", example = "123.45", required = true)
    private float floatColumn;

    @ApiModelProperty(value = "双精度浮点数", example = "123456.789", required = true)
    private double doubleColumn;

    @ApiModelProperty(value = "布尔列", example = "true", required = true)
    private boolean booleanColumn;

    @ApiModelProperty(value = "日期列", example = "2024-01-01 00:00:00", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date dateColumn;

    @ApiModelProperty(value = "时间列", example = "12:00:00", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timeColumn;

    @ApiModelProperty(value = "时间戳列", example = "2024-01-01 12:00:00", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestampColumn;

    @ApiModelProperty(value = "二进制大对象", required = false)
    private byte[] blobColumn;

    @ApiModelProperty(value = "状态", required = false)
    private Integer status;

    @ApiModelProperty(value = "创建时间", example = "12:00:00", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间", example = "12:00:00", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
