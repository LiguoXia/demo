package com.liguo.demo.consume.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * topic11消费
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/8 22:19
 * @since 0.0.1
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "topic11", consumerGroup = "groupDemo1")
public class PersonMqListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String message) {
        try {
            // 消费逻辑
            System.out.println("topic11 Listener Received message: " + message);
            // 模拟失败
            throw new RuntimeException("Simulated consumer failure");
        } catch (Exception e) {
            // 消费失败，抛出异常或记录错误日志
            throw new RuntimeException("消费失败，消息将重试", e);
        }
    }
}
