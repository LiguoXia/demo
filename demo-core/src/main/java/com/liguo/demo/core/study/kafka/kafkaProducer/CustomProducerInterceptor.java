package com.liguo.demo.core.study.kafka.kafkaProducer;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * kafka自定义生产者拦截器
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/12 9:21
 * @since 0.0.1
 */
@Slf4j
public class CustomProducerInterceptor implements ProducerInterceptor {

    /**
     * 在发送前做一些处理
     *
     * @param record
     * @return
     */
    @Override
    public ProducerRecord onSend(ProducerRecord record) {
        log.info("拦截器-正在发送消息: {}", record.value().toString());
        return record;
    }

    /**
     * 在消息应答前，或者消息发送失败时被调用
     *
     * @param metadata
     * @param exception
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        log.info("在消息应答前，或者消息发送失败时被调用,metadata:{}, exception", metadata, exception);
    }

    /**
     * 关闭interceptor，主要用于执行一些资源清理工作
     */
    @Override
    public void close() {
        log.info("关闭interceptor，主要用于执行一些资源清理工作");
    }

    /**
     * 获取配置信息和初始化数据时调用
     *
     * @param configs
     */
    @Override
    public void configure(Map<String, ?> configs) {
        log.info("获取配置信息和初始化数据时调用, configs:{}", JSON.toJSONString(configs));
    }
}
