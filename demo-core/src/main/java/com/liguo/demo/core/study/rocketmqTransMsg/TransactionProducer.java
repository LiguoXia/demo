package com.liguo.demo.core.study.rocketmqTransMsg;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事务消息生产者
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/9 16:41
 * @since 0.0.1
 */
@Slf4j
@Component
public class TransactionProducer {

    private String producerGroup = "order_trans_group";
    private TransactionMQProducer producer;

    //用于执行本地事务和事务状态回查的监听器
    @Autowired
    OrderTransactionListener orderTransactionListener;
    //执行任务的线程池
    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(50), new CustomizableThreadFactory("rocketMq-transaction-thread-"));

    @PostConstruct
    public void init() {
        producer = new TransactionMQProducer(producerGroup);
        producer.setNamesrvAddr("192.168.18.23:9876");
        producer.setSendMsgTimeout(Integer.MAX_VALUE);
        producer.setExecutorService(executor);
        producer.setTransactionListener(orderTransactionListener);
        this.start();
    }

    private void start() {
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    // 事务消息发送
    public TransactionSendResult send(String data, String topic) throws MQClientException, InterruptedException {
        Message message = new Message(topic, data.getBytes());
        TransactionSendResult result = this.producer.sendMessageInTransaction(message, null);
        log.info("发送Mq半事务消息成功,返回:{}", JSON.toJSONString(result));
        return result;
    }
}