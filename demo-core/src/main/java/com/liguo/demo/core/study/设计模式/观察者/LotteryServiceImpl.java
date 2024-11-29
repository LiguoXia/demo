package com.liguo.demo.core.study.设计模式.观察者;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/26 23:28
 * @since 0.0.1
 */
@Slf4j
public class LotteryServiceImpl extends LotteryService {
    @Override
    protected LotteryResult doDraw(String uId) {
        MinibusTargetService minibusTargetService = new MinibusTargetService();
        // 摇号
        String lottery = minibusTargetService.lottery(uId);
        // 结果
        return new LotteryResult(uId, lottery, new Date(), lottery);
    }
}
