package com.liguo.demo.core.study.rocketmqTransMsg;

import lombok.Data;

/**
 * 订单传输对象
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/9 16:46
 * @since 0.0.1
 */
@Data
public class OrderDTO {

    private String commodityCode;

    private Long id;

    private String orderNo;
}
