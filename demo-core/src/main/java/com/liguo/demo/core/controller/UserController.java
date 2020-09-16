package com.liguo.demo.core.controller;


import com.liguo.demo.core.pojo.vo.HttpResult;
import com.liguo.demo.core.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息前端控制器
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/14
 * @since 0.0.1
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @ApiOperation("测试方法")
    @PostMapping("/getUser")
    public HttpResult test(@ApiParam("ID") @RequestParam("id") String id) {
        log.info("请求参数:{}", id);
        return HttpResult.success(iUserService.getById(id));
    }
}
