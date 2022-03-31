package com.liguo.demo.core.controller;

import com.example.aspectlogspringbootstarter.aop.AspectLog;
import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.test.async.AsyncTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * desc:
 *
 * @author liguo
 * @create 2020-09-09
 */
@Api(description = "测试接口")
@RestController
@RequestMapping("/asyncTest")
@Slf4j
public class AsyncTestController {
    @Autowired
    private AsyncTest asyncTest;

    @AspectLog
    @ApiOperation("测试方法")
    @PostMapping("/test")
    public Result test() throws Exception {
//        asyncTest.doTaskOne();
//        asyncTest.doTaskTwo();
//        asyncTest.doTaskThree();
        asyncTest.doAsyncTaskOne();
        asyncTest.doAsyncTaskTwo();
        asyncTest.doAsyncTaskThree();
        return Result.success();
    }

}
