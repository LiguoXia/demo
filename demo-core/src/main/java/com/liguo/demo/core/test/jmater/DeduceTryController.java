package com.liguo.demo.core.test.jmater;

import com.example.aspectlogspringbootstarter.aop.AspectLog;
import com.liguo.demo.core.pojo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * 推导试跑控制器
 *
 * @author 01395755[xialiguo]
 * @version 4.4
 * @date 2022/3/22 14:43
 * @since 4.4
 */
@Slf4j
@RestController
@Api(tags = "资金计划推导检查相关接口", description = "资金计划推导检查相关接口")
@RequestMapping("/oms/bseg/deduceTry")
public class DeduceTryController {

    @Autowired
    private IDeduceTryService deduceTryService;

    @AspectLog
    @ApiOperation("试推导")
    @PostMapping("tryDeduce")
    public Result<DeduceTryResp> tryDeduce (@ApiParam(value = "试推导参数", required = true) @Validated @RequestBody DeduceTryReq req) throws ExecutionException, InterruptedException {
        // TODO 租户
        return Result.success(deduceTryService.deduceTry(req));
    }
}
