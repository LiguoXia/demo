package com.liguo.demo.core.controller;


import com.liguo.demo.core.factory.UserContext;
import com.liguo.demo.core.pojo.entity.User;
import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    UserContext userContext;
    @ApiOperation("测试方法")
    @PostMapping("/getUserTask")
    public Result getUserTask(@ApiParam("用户对象") @RequestBody User user) {
        log.info("请求参数:{}", user.toString());
        IUserService userService = userContext.getUserService(user.getType());
        return Result.success(userService.task());
    }
}
