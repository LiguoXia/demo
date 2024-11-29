package com.liguo.demo.core.controller;


import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.service.IDyrPorderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author liguo
 * @since 2023-02-27
 */
@Api(tags = "话费充值接口")
@RestController
@RequestMapping("/core/dyr-porder")
public class DyrPorderController {
    @Autowired
    private IDyrPorderService dyrPorderService;

    @ApiOperation("接口存在多个实现类时的动态调用")
    @PostMapping("/restTemplate")
    public Result restTemplate() throws UnsupportedEncodingException {
        dyrPorderService.getAll();
        return Result.success();
    }
}
