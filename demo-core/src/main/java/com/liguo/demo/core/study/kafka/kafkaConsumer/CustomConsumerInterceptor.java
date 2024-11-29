package com.liguo.demo.core.study.kafka.kafkaConsumer;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.Iterator;
import java.util.Map;

/**
 * kafka 消费者拦截器
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/12 9:24
 * @since 0.0.1
 */
@Slf4j
public class CustomConsumerInterceptor implements ConsumerInterceptor {

    /**
     * 拉取消息时，被调用
     *
     * @param records
     * @return
     */
    @Override
    public ConsumerRecords onConsume(ConsumerRecords records) {
        log.error("拦截器-消息{}已被拉取", records.toString());
        return records;
    }

    @Override
    public void close() {

    }

    /**
     * 提交请求响应成功时被调用
     *
     * @param offsets
     */
    @Override
    public void onCommit(Map offsets) {
        log.info("提交请求响应成功时被调用, offsets:");
        Iterator<Map.Entry<Object, Object>> iterator = offsets.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> entry = iterator.next();
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("configs:{}", JSON.toJSONString(configs));
    }
}
