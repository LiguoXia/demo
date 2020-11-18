package com.liguo.demo.core.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liguo.demo.core.config.BizException;
import com.liguo.demo.core.pojo.dos.TParamConfig;
import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.pojo.vo.ValidationResult;
import com.liguo.demo.core.service.ITParamConfigService;
import com.liguo.demo.core.util.BeanValidateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 资金计划业务控制表 前端控制器
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/15 23:51
 * @since 0.0.1
 */
@Slf4j
@Api(description = "资金计划业务控制表 前端控制器")
@RestController
@RequestMapping("/core/pf")
public class TParamConfigController {
    @Autowired
    ITParamConfigService itParamConfigService;

    @ApiOperation("保存")
    @PostMapping("/save")
    public Result save(@ApiParam("资金计划业务控制对象") @RequestBody TParamConfig tParamConfig) {
        log.info("请求参数:{}", tParamConfig.toString());
        ValidationResult vr = BeanValidateUtil.validateEntity(tParamConfig);
        ValidationResult vr1 = BeanValidateUtil.validateProperty(tParamConfig, "paramKey");
        String errorMsg = BeanValidateUtil.validateReturnMsg(tParamConfig);
        BeanValidateUtil.validate(tParamConfig);
        try {
            itParamConfigService.save(tParamConfig);
        } catch (Exception e) {
            // 抛出去让全局异常处理
            throw new BizException(505, "插入失败:" + e.getMessage());
        }
        return Result.success();
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public Result delete(@ApiParam("资金计划业务控制对象") @RequestParam Long id) {
        log.info("请求参数:{}", id);
        try {
            itParamConfigService.removeById(id);
        } catch (Exception e) {
            // 抛出去让全局异常处理
            throw new BizException(505, "删除失败:" + e.getMessage());
        }
        return Result.success();
    }

    @ApiOperation("更新")
    @PostMapping("/updat")
    public Result updat(@ApiParam("资金计划业务控制对象") @RequestBody TParamConfig tParamConfig) {
        log.info("请求参数:{}", tParamConfig.toString());
        try {
            itParamConfigService.updateById(tParamConfig);
        } catch (Exception e) {
            // 抛出去让全局异常处理
            throw new BizException(505, "删除失败:" + e.getMessage());
        }
        return Result.success();
    }

    @ApiOperation("查询")
    @PostMapping("/query")
    public Result query(@ApiParam("键值") @RequestParam String key) {
        log.info("请求参数:{}", key);
        LambdaQueryWrapper<TParamConfig> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(TParamConfig::getParamKey, key);
        for (int i = 0; i < 10000; i++) {
            itParamConfigService.getOne(lambdaQueryWrapper);
        }
        return Result.success(itParamConfigService.getOne(lambdaQueryWrapper));
    }
}
