package com.liguo.demo.core.service.pay;

import com.liguo.demo.core.pojo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付测试
 * <p>http://localhost:8001/pay/test?payType=WECHAT</p>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/29 22:30
 * @since 0.0.1
 */
@Api(description = "测试接口")
@RestController
@RequestMapping("/pay")
@Slf4j
public class PayTsetController {

    @ApiOperation("接口存在多个实现类时的动态调用")
    @GetMapping("/test")
    public Result test(@ApiParam("支付方式") @RequestParam("payType") String payType) {
        PayService mode1 = PayChooseContext.getPayMode(PayTypeEnum.ALIPAY);
        PayService mode = PayChooseContext.getPayMode(PayTypeEnum.valueOf(payType));
        String result = mode.pay(888L);
        return Result.success(result);
    }
}
