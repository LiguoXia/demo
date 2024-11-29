package com.liguo.demo.consume.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * binlog消费
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/8 22:19
 * @since 0.0.1
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "groupDemo2", topic = "canal_topic")
public class CanalListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        try {
            // 消费逻辑
            System.out.println("CanalListener Received message: " + message);
        } catch (Exception e) {
            // 消费失败，抛出异常或记录错误日志
            throw new RuntimeException("消费失败，消息将重试", e);
        }
    }
}
