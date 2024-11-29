package com.liguo.demo.core.study.设计模式.观察者;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/26 23:23
 * @since 0.0.1
 */
@Slf4j
public class MessageEventListener implements EventListener {
    @Override
    public void doEvent(LotteryResult result) {
        log.info("给用户 {} 发送短信通知(短信)： {}", result.getUId(), result.getMsg());
    }
}
