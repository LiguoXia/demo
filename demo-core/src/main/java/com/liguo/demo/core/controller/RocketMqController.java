package com.liguo.demo.core.controller;

import com.liguo.demo.core.service.RocketMqHelper;
import com.liguo.demo.core.study.rocketmqTransMsg.OrderDTO;
import com.liguo.demo.core.study.rocketmqTransMsg.OrderService;
import com.liguo.demo.core.study.rocketmqTransMsg.OrderTransactionListener;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "RocketMq接口")
@RestController
@RequestMapping("/rocketMq")
@Slf4j
public class RocketMqController {

    public static int i = 0;
    @Autowired
    private RocketMqHelper rocketMqHelper;

    @ApiOperation("发送普通rocketMq消息")
    @GetMapping("/sendNor")
    public String test() {
        rocketMqHelper.sendMessage("topic11", "Hello RocketMQ-死信test" + (++i));
        return "Message sent!";
    }

    /**
     * @see OrderTransactionListener
     */
    @Autowired
    OrderService orderService;
    @ApiOperation("发送事务rocketMq消息")
    @PostMapping("/create_order")
    public void createOrder(@RequestBody OrderDTO order) throws MQClientException, InterruptedException {
        log.info("接收到订单数据：{}", order.getCommodityCode());
        orderService.createOrder(order);
    }

    @Autowired
    @ApiOperation("发送带有 key 的消息（方便 RocketMQ 做去重）")
    @PostMapping("/syncSendWithKey")
    public void syncSendWithKey() {
        rocketMqHelper.syncSendWithKey("order", "msg1111", "key111");
    }

    @Autowired
    @ApiOperation("发送延时消息")
    @PostMapping("/syncSendWithDelay")
    public void syncSendWithDelay() {
        rocketMqHelper.syncSendWithDelay("order", "msg-delay", 3);
    }
}
