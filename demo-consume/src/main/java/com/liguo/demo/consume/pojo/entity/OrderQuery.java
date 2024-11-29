package com.liguo.demo.consume.pojo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(description = "订单查询对象")
@Data
public class OrderQuery {

    @ApiModelProperty(value = "订单ID", example = "1", required = true)
    private Long orderId;

    @ApiModelProperty(value = "用户ID", example = "2", required = true)
    private Integer userId;

}
