package com.liguo.demo.core.study.rocketmqTransMsg;

import org.apache.rocketmq.client.exception.MQClientException;

/**
 * 订单服务接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/9 16:45
 * @since 0.0.1
 */
public interface OrderService {

    void createOrder(OrderDTO orderDTO,String transactionId);

    void createOrder(OrderDTO order) throws MQClientException, InterruptedException;
}
