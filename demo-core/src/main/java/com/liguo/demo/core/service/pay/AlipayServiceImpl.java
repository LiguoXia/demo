package com.liguo.demo.core.service.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 支付宝支付
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/29 22:30
 * @since 0.0.1
 */
@Slf4j
@Service
public class AlipayServiceImpl implements PayService{

    @Override
    public PayTypeEnum getCode() {
        return PayTypeEnum.ALIPAY;
    }

    /**
     * 支付接口
     *
     * @param money 金额
     * @return 支付成功
     */
    @Override
    public String pay(Long money) {
        log.info("支付宝支付:{}", money);
        return "支付宝支付";
    }
}
