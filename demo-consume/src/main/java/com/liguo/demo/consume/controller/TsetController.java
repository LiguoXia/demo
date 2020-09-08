package com.liguo.demo.consume.controller;

import com.liguo.demo.consume.fegin.TestFeginClient;
import com.liguo.demo.consume.vo.HttpResult;
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
@RestController
@RequestMapping("/cc/dd")
@Slf4j
public class TsetController {
    @Autowired
    private TestFeginClient testFeginClient;

    @PostMapping("/test")
    public HttpResult test(@RequestParam("name") String name) {
        log.info("请求参数:{}", name);
        HttpResult httpResult = testFeginClient.test(name);
        return HttpResult.success("Helle:" + name + "这是消费服务"+ "调用核心服务返回:" + httpResult.getData());
    }
}
