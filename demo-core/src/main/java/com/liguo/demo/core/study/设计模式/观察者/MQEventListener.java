package com.liguo.demo.core.study.设计模式.观察者;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/26 23:25
 * @since 0.0.1
 */
@Slf4j
public class MQEventListener implements EventListener{
    @Override
    public void doEvent(LotteryResult result) {
        log.info("记录用户 {} 摇号结果(MQ)： {}", result.getUId(), result.getMsg());
    }
}
