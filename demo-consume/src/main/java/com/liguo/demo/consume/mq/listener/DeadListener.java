package com.liguo.demo.consume.mq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * 死信队列消费
 * https://help.aliyun.com/zh/apsaramq-for-rocketmq/cloud-message-queue-rocketmq-5-x-series/developer-reference/dead-letter-messages
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/8 22:19
 * @since 0.0.1
 */
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "groupDemo3", topic = "%DLQ%groupDemo1")
public class DeadListener implements RocketMQListener<MessageExt> {

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
            String thisTopic = messageExt.getTopic();
            String originalTopic = messageExt.getProperty("ORIGINAL_TOPIC");  // 获取原始主题
            log.info("Dead letter message from topic:{},msg:{}", originalTopic, messageContent);
            // 根据不同主题的消息进行不同的处理
            // 消费逻辑
        } catch (Exception e) {
            // 消费失败，抛出异常或记录错误日志
            throw new RuntimeException("消费失败，消息将重试", e);
        }
    }
}
