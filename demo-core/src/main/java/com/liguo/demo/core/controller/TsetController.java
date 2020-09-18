package com.liguo.demo.core.controller;

import com.liguo.demo.core.enums.TrafficCodeEnum;
import com.liguo.demo.core.factory.TrafficModeFactory;
import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.service.IMailService;
import com.liguo.demo.core.service.TrafficModeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 *
 * @author liguo
 * @create 2020-09-09
 */
@Api(description = "测试接口")
@RestController
@RequestMapping("/aa/bb")
@Slf4j
public class TsetController {
    @Autowired
    private IMailService iMailService;

    @ApiOperation("测试方法")
    @PostMapping("/test")
    public Result test(@ApiParam("用户名") @RequestParam("name") String name) {
        log.info("请求参数:{}", name);
        iMailService.sendSimpleMail("xialiguo0212@163.com", "title", "正文：" + name + "你好!");
        return Result.success("Helle:" + name + "这是核心服务");
    }

    @ApiOperation("接口存在多个实现类时的动态调用")
    @PostMapping("/test1")
    public Result testImplSelect(@ApiParam("用户名") @RequestParam("name") String name) {
        log.info("请求参数:{}", name);
        TrafficModeService mode = TrafficModeFactory.getTrafficMode(TrafficCodeEnum.BUS);

        return Result.success("Helle:" + name + "这是核心服务,返回:" + mode.getFee());
    }
}
