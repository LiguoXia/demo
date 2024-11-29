package com.liguo.demo.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Kafka接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/10 16:03
 * @since 0.0.1
 */
@Api(tags = "Kafka接口")
@Slf4j
@RestController
public class KafkaController {
    // Kafka模板用来向kafka发送数据
    @Autowired
    public KafkaTemplate<String, Object> kafkaTemplate;

    /**
     * 普通的发送
     */
    @ApiOperation("普通的发送")
    @GetMapping("/send/{msg}")
    public String send(@PathVariable("msg") String msg) {
        kafkaTemplate.send("ldy-1", msg);
        return "success";
    }

    /**
     * 方法一、带回调的生产者发送消息
     */
    @ApiOperation("方法一、带回调的生产者发送消息")
    @GetMapping("/send/callback/one/{msg}")
    public String sendCallbackOneMessage(@PathVariable("msg") String msg) {
        kafkaTemplate.send("ldy-1", msg).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功===>topic：" + topic + "，partition：" + partition + "，offset：" + offset);
        }, throwable -> {
            System.out.println("发送消息失败===>" + throwable.getMessage());
        });
        return "success";
    }

    /**
     * 方法二、带回调的生产者发送消息
     */
    @ApiOperation("方法二、带回调的生产者发送消息")
    @GetMapping("/send/callback/two/{msg}")
    public String sendCallbackTwoMessage(@PathVariable("msg") String msg) {
        kafkaTemplate.send("ldy-1", msg).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                // 消息发送到的topic
                String topic = result.getRecordMetadata().topic();
                // 消息发送到的分区
                int partition = result.getRecordMetadata().partition();
                // 消息在分区内的offset
                long offset = result.getRecordMetadata().offset();
                System.out.println("发送消息成功===>topic：" + topic + "，partition：" + partition + "，offset：" + offset);
            }
        });
        return "success";
    }

    /**
     * 指定消息发送到那个主题的那个分区
     */
    @ApiOperation("指定消息发送到那个主题的那个分区")
    @GetMapping("/send/partition/{msg}")
    public String sendToPartition(@PathVariable("msg") String msg) {
        kafkaTemplate.send("ldy-2", 1, null, msg);
        return "success";
    }

    /**
     * 生产者事务发送：需配置transaction-id-prefix开启事务
     *
     * @param msg 消息内容
     * @author yh
     * @date 2022/5/11
     */
    @Transactional
    @RequestMapping("/transaction")
    public void transaction(String msg) {
        kafkaTemplate.send("ldy-2", msg);
        int a = 1 / 0;
        kafkaTemplate.send("ldy-2", "_____" + msg);
    }

    /**
     * 第二种事务发送
     *
     * @param msg 消息内容
     * @author yh
     * @date 2022/5/11
     */
    @ApiOperation("事务发送")
    @RequestMapping("/transaction2")
    public void transaction2(String msg) {
        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback() {
            @Override
            public Object doInOperations(KafkaOperations kafkaOperations) {
                kafkaOperations.send("ldy-2", msg);
                int a = 1 / 0;
                return true;
            }
        });
    }
}
