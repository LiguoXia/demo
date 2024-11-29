package com.liguo.demo.core.study.kafka.case1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/8 22:01
 * @since 0.0.1
 */
public class KafkaProducerExample {
    public static void main(String[] args) {
        // 配置 Kafka 生产者
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.18.32:9092"); // Kafka 集群地址
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // 创建 Kafka 生产者
        Producer<String, String> producer = new KafkaProducer<>(properties);

        // 发送消息到指定主题
        String topic = "test-topic";
        String key = "key1";
        String value = "Hello, Kafka!";
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);

        // 异步发送消息
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                System.out.println("消息发送成功，偏移量：" + metadata.offset());
            } else {
                System.err.println("消息发送失败: " + exception.getMessage());
            }
        });

        // 关闭生产者
        producer.close();
    }
}
