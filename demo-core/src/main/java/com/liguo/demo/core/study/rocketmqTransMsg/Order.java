package com.liguo.demo.core.study.rocketmqTransMsg;

import lombok.Data;

/**
 * 订单表
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/9 16:48
 * @since 0.0.1
 */
@Data
public class Order {

    private String commodityCode;

    private Long id;

    private String orderNo;
}
