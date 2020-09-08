package com.liguo.demo.core.controller;

import com.liguo.demo.core.vo.HttpResult;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/aa/bb")
@Slf4j
public class TsetController {
    @PostMapping("/test")
    public HttpResult test(@RequestParam("name") String name) {
        log.info("请求参数:{}", name);
        return HttpResult.success("Helle:" + name + "这是核心服务");
    }
}
