package com.liguo.demo.core.study.kafka.case1;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/8 22:13
 * @since 0.0.1
 */
public class KafkaConsumerExample {
    public static void main(String[] args) {
        // 配置 Kafka 消费者
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.18.32:9092"); // Kafka 集群地址
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group"); // 消费者组 ID
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // 创建 Kafka 消费者
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);

        // 订阅指定主题
        String topic = "test-topic";
        consumer.subscribe(Collections.singletonList(topic));

        // 持续消费消息
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            records.forEach(record -> {
                System.out.println("接收到消息：" + record.value());
            });
        }
    }
}
