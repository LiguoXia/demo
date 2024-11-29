package com.liguo.demo.core.study.rocketmqTransMsg;

import org.apache.ibatis.annotations.Mapper;

/**
 * 订单dao接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/9 16:47
 * @since 0.0.1
 */
@Mapper
public interface OrderMapper {

    void createOrder(Order order);
}
