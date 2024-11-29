package com.liguo.demo.consume.mq.listener;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 订单消费
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/8 22:19
 * @since 0.0.1
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "groupDemo3", topic = "order")
public class OrderListener implements RocketMQListener<MessageExt> {

    /**
     * 通常是因为 RocketMQ 消费者在接收消息时，RocketMQ 发送的消息并没有被自动转换为 Spring 的 Message 对象。
     * RocketMQ 默认使用 org.apache.rocketmq.common.message.MessageExt，而不是 Spring Messaging 的 Message，因此会导致类型转换问题。
     * @param messageExt
     */
    @Override
    public void onMessage(MessageExt messageExt) {
        try {
            // 获取消息体内容
            String messageContent = new String(messageExt.getBody());


            // 从 headers 中获取 key
            String messageKey = messageExt.getKeys();
            if (messageKey != null) {
                System.out.println("Message key: " + messageKey);
            } else {
                System.out.println("No key found for the message.");
            }
            System.out.println("OrderListener total message: " + JSON.toJSONString(messageExt));


            String originalTopic = messageExt.getTopic();  // 获取原始主题
            System.out.println("Dead letter message from topic: " + originalTopic);
            // 根据不同主题的消息进行不同的处理


            // 消费逻辑
            System.out.println("OrderListener Received message: " + messageContent);
        } catch (Exception e) {
            // 消费失败，抛出异常或记录错误日志
            throw new RuntimeException("消费失败，消息将重试", e);
        }
    }
}
