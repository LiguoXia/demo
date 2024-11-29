package com.liguo.demo.consume.controller;

import com.liguo.demo.consume.feign.client.TestFeginClient;
import com.liguo.demo.consume.pojo.vo.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 *
 * @author liguo
 * @create 2020-09-09
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/cc/dd")
@Slf4j
public class TsetController {
    @Autowired
    private TestFeginClient testFeginClient;

    @ApiOperation("测试")
    @PostMapping("/test")
    public HttpResult test(@RequestParam("name") String name) {
        log.info("请求参数:{}", name);
        HttpResult httpResult = testFeginClient.test(name);
        return HttpResult.success("Helle:" + name + "这是消费服务"+ "调用核心服务返回:" + httpResult.getData());
    }
}
