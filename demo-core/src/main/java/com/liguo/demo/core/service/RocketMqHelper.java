package com.liguo.demo.core.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/8 21:16
 * @since 0.0.1
 */
@Slf4j
@Component
public class RocketMqHelper {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送消息
     *
     * @param topic 主题
     * @param message 消息内容
     * @return SendResult 发送结果
     */
    public SendResult sendMessage(String topic, String message) {
        // rocketMQTemplate.convertAndSend(topic, message);
        // rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(message).build());
        SendResult result = rocketMQTemplate.syncSend(topic, message);
        log.info("发送rocketMq返回:{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 带延时级别的同步发送
     * 1	1 秒
     * 2	5 秒
     * 3	10 秒
     * 4	30 秒
     * 5	1 分钟
     * 6	2 分钟
     * 7	3 分钟
     * 8	4 分钟
     * 9	5 分钟
     * 10	10 分钟
     * 11	20 分钟
     * 12	30 分钟
     * 13	1 小时
     * 14	2 小时
     * 15	3 小时
     * 16	4 小时
     * 17	5 小时
     * 18	6 小时
     * 19	7 小时
     * 20	8 小时
     * 21	9 小时
     * 22	10 小时
     * 23	11 小时
     * 24	12 小时
     * 25	13 小时
     * 26	14 小时
     * 27	15 小时
     * 28	16 小时
     * 29	17 小时
     * 30	18 小时
     * 31	19 小时
     * 32	20 小时
     * 33	21 小时
     * 34	22 小时
     * 35	23 小时
     * 36	24 小时
     *
     * @param topic 主题
     * @param message 消息内容
     * @param delayLevel 延时级别
     * @return SendResult 发送结果
     */
    public SendResult syncSendWithDelay(String topic, String message, int delayLevel) {
        return rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(message).build(), 3000, delayLevel);
    }

    public void sendMessageWithTag(String topic, String tag, String message) {
        rocketMQTemplate.convertAndSend(topic + ":" + tag, message);
    }

    public void sendMessageWithCustomProperties(String topic, String message, String key, String value) {
        rocketMQTemplate.send(topic, MessageBuilder.withPayload(message)
                .setHeader(key, value)
                .build());
    }

    /**
     * 异步发送消息
     *
     * @param topic 主题
     * @param message 消息内容
     */
    public void asyncSend(String topic, String message) {
        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(message).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("异步消息发送成功：" + sendResult);
            }

            @Override
            public void onException(Throwable throwable) {
                System.err.println("异步消息发送失败：" + throwable.getMessage());
            }
        });
    }

    /**
     * 单向发送消息（不关心发送结果）
     *
     * @param topic 主题
     * @param message 消息内容
     */
    public void sendOneWay(String topic, String message) {
        rocketMQTemplate.sendOneWay(topic, MessageBuilder.withPayload(message).build());
    }

    /**
     * 发送带有 key 的消息（方便 RocketMQ 做去重）
     *
     * @param topic 主题
     * @param message 消息内容
     * @param key 消息的唯一 key
     */
    public SendResult syncSendWithKey(String topic, String message, String key) {
        Message<String> msg = MessageBuilder.withPayload(message)
                .setHeader(RocketMQHeaders.KEYS, key)
                .build();
        return rocketMQTemplate.syncSend(topic, msg);
    }
}
