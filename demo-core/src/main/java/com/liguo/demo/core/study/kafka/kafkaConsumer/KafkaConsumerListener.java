package com.liguo.demo.core.study.kafka.kafkaConsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * kafka下
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/12 10:12
 * @since 0.0.1
 */
@Slf4j
@Component
public class KafkaConsumerListener {
    /**
     * 指定一个主题
     */
    /*@KafkaListener(topics = "ldy-1", groupId = "group-ldy-1")
    public void ListenerTopic(ConsumerRecord<String, Object> record) {
        System.out.println("指定一个主题===>topic：" + record.topic() +
                "，partition：" + record.partition() +
                "，offset：" + record.offset() +
                "，key：" + record.key() +
                "，value：" + record.value());
    }
*/
    /**
     * 手动提交需要指定
     *
     * listener:
     *       # 在侦听器容器中运行的线程数，一般设置为 机器数*分区数
     *       concurrency: 9
     *       # 设置批量消费
     *       type: single
     *       # RECORD:当每一条记录被消费者监听器（ListenerConsumer）处理之后提交
     *       # BATCH:当每一批poll()的数据被消费者监听器（ListenerConsumer）处理之后提交
     *       # TIME:当每一批poll()的数据被消费者监听器（ListenerConsumer）处理之后，距离上次提交时间大于TIME时提交
     *       # COUNT:当每一批poll()的数据被消费者监听器（ListenerConsumer）处理之后，被处理record数量大于等于COUNT时提交
     *       # COUNT_TIME:TIME |　COUNT　有一个条件满足时提交
     *       # MANUAL:当每一批poll()的数据被消费者监听器（ListenerConsumer）处理之后, 手动调用Acknowledgment.acknowledge()后提交
     *       # MANUAL_IMMEDIATE:手动调用Acknowledgment.acknowledge()后立即提交
     *       # ack模式
     *       ack-mode: MANUAL_IMMEDIATE
     *
     * @param record 消息内容
     * @param ack    应答
     * @author yh
     * @date 2022/5/10
     */
    @KafkaListener(topics = "ldy-1", groupId = "group-ldy-1")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("手动提交");
        System.out.println(record);
        System.out.println(record.value());
        // 消息处理下游绑定事务，成功消费后提交ack
        // 手动提交offset
        ack.acknowledge();
    }
}
