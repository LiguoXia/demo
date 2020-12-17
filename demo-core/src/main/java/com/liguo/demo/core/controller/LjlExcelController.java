package com.liguo.demo.core.controller;


import com.liguo.demo.core.factory.UserContext;
import com.liguo.demo.core.pojo.entity.ThreeFlow;
import com.liguo.demo.core.pojo.entity.User;
import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.service.IThreeFlowService;
import com.liguo.demo.core.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/ljl")
public class LjlExcelController {
    @Autowired
    private IThreeFlowService threeFlowService;
    @ApiOperation("删除冲正交易数据")
    @PostMapping("/deleteCz")
    public Result getUserTask() {
        log.info("请求参数:{}");
        threeFlowService.delete();
        return Result.success();
    }
}
