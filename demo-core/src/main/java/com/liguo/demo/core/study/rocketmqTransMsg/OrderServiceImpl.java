package com.liguo.demo.core.study.rocketmqTransMsg;

import cn.hutool.core.lang.Snowflake;
import com.alibaba.fastjson2.JSON;
import org.apache.rocketmq.client.exception.MQClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 订单实现类
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/9 16:47
 * @since 0.0.1
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    TransactionLogMapper transactionLogMapper;
    @Autowired
    TransactionProducer producer;

    Snowflake snowflake = new Snowflake(1,1);
    Logger logger = LoggerFactory.getLogger(this.getClass());

    //执行本地事务时调用，将订单数据和事务日志写入本地数据库
    @Transactional
    @Override
    public void createOrder(OrderDTO orderDTO, String transactionId) {
        //1.创建订单
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        orderMapper.createOrder(order);

        //2.写入事务日志
        TransactionLog log = new TransactionLog();
        log.setId(transactionId);
        log.setBusiness("order");
        log.setForeignKey(String.valueOf(order.getId()));
        transactionLogMapper.insert(log);
        logger.info("订单创建完成。{}",orderDTO);

    }

    //前端调用，只用于向RocketMQ发送事务消息
    @Override
    public void createOrder(OrderDTO order) throws MQClientException, InterruptedException {
        order.setId(snowflake.nextId());
        order.setOrderNo(snowflake.nextIdStr());
        producer.send(JSON.toJSONString(order),"order");
    }
}
