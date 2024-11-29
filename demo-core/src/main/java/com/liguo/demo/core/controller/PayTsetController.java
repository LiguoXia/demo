package com.liguo.demo.core.controller;

import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.service.pay.PayChooseContext;
import com.liguo.demo.core.service.pay.PayService;
import com.liguo.demo.core.service.pay.PayTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 支付测试
 * <p>http://localhost:8001/pay/test?payType=WECHAT</p>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/29 22:30
 * @since 0.0.1
 */
@Api(tags = "支付测试")
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayTsetController {
    // 有多个实现启动就会报错
    //@Autowired
    //private PayService payService;

    // 1 可以在某个实现指定@Primary

    // 2 可以用某个类名称指定注入
    @Autowired
    private PayService alipayServiceImpl;

    // 注入多个实现
    @Autowired
    private List<PayService> PayServiceList;

    // 通过map
    // key0:alipayServiceImpl -> {AlipayServiceImpl@14624}
    // key1:wechatPayServiceImpl -> {WechatPayServiceImpl@15047}
    @Autowired
    private Map<String, PayService> PayServiceMap;

    @ApiOperation("接口存在多个实现类时的动态调用")
    @GetMapping("/test")
    public Result test(@ApiParam("支付方式") @RequestParam("payType") String payType) {
        alipayServiceImpl.pay(1L);


        PayService mode1 = PayChooseContext.getPayMode(PayTypeEnum.ALIPAY);
        PayService mode = PayChooseContext.getPayMode(PayTypeEnum.valueOf(payType));
        String result = mode.pay(888L);
        return Result.success(result);
    }
}
