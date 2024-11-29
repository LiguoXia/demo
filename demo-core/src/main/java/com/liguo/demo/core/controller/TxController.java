package com.liguo.demo.core.controller;

import com.liguo.demo.core.dao.DemoMapper;
import com.liguo.demo.core.pojo.entity.Demo;
import com.liguo.demo.core.service.TxDemoService;
import com.liguo.demo.core.service.impl.Tx2DemoServiceImpl;
import com.liguo.demo.core.study.反射与代理.AopTargetUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

/**
 * 事务测试接口
 *
 * @author xialiguo0212@gmail.com
 * @versio 0.0.1
 * @date 2023/12/29 16:42
 * @since 0.0.1
 */
@Api(tags = "事务测试接口")
@Slf4j
@RestController
@RequestMapping("/tx")
public class TxController extends HttpServlet {
    //  `spring.aop.proxy-target-class`配置为false时这里时jdk动态代理
    @Autowired
    private TxDemoService txDemoService;
    // 没有接口只能cglib动态代理
    @Autowired
    private Tx2DemoServiceImpl tx2DemoService;
    @Autowired
    private DemoMapper demoMapper;


    @ApiOperation("源码debug有接口实现")
    @PostMapping("/sourceDebug")
    public void sourceDebug(@RequestBody Demo demo) {
        txDemoService.update1(demo);
    }

    @ApiOperation("源码debug无接口")
    @PostMapping("/sourceDebug2")
    public void sourceDebug2(@RequestBody Demo demo) throws Exception {
        tx2DemoService.update(demo);

        // 方式1 获取原始类
        TxDemoService t1 = (TxDemoService) AopTargetUtils.getTarget(txDemoService);
        Tx2DemoServiceImpl t2 = (Tx2DemoServiceImpl) AopTargetUtils.getTarget(tx2DemoService);

        // 方式2 获取原始类
        Advised advised = (Advised) txDemoService;
        Object obj = advised.getTargetSource().getTarget();
        System.out.println(t1 == obj);

        // 方式3 采用Spring自带的工具类获取—AopProxyUtils.getSingletonTarget
        Object obj1 = AopProxyUtils.getSingletonTarget(txDemoService);
    }

}
