package com.liguo.demo.core.study.设计模式.观察者;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/26 23:32
 * @since 0.0.1
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        LotteryService lotteryService = new LotteryServiceImpl();
        LotteryResult result = lotteryService.draw("2765789109876");
        log.info("测试结果： {}", JSON.toJSONString(result));
    }
}
