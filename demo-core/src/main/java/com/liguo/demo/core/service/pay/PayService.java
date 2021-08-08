package com.liguo.demo.core.service.pay;

import com.liguo.demo.core.enums.TrafficCodeEnum;

/**
 * 支付接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/29 22:29
 * @since 0.0.1
 */
public interface PayService {

    PayTypeEnum getCode();

    /**
     * 支付接口
     *
     * @param money 金额
     * @return 支付成功
     */
    String pay(Long money);
}
