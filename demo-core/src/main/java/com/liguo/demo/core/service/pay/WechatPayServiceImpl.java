package com.liguo.demo.core.service.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 微信支付
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/29 22:32
 * @since 0.0.1
 */
@Slf4j
@Service
public class WechatPayServiceImpl implements PayService{

    @Override
    public PayTypeEnum getCode() {
        return PayTypeEnum.WECHAT;
    }

    /**
     * 支付接口
     *
     * @param money 金额
     * @return 支付成功
     */
    @Override
    public String pay(Long money) {
        log.info("微信支付:{}", money);
        return "微信支付";
    }
}
