package com.liguo.demo.core.controller;

import com.liguo.demo.core.config.webmvcconfig.LoginUser;
import com.liguo.demo.core.config.webmvcconfig.SysUser;
import com.liguo.demo.core.pojo.Request.ParamRequest;
import com.liguo.demo.core.pojo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 前后端各种传参形式
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/12/21 8:47
 * @since 0.0.1
 */
@Api(tags = "前后端各种传参形式")
@Slf4j
@RestController
@RequestMapping("/param")
public class ParamController {

    /**
     * get请求传的是key/value形式的数据，在后台应该用@RequestParam接收；获取请求参数的（包括post表单提交）
     *
     * @param param1
     * @param param2
     * @return Result
     */
    @GetMapping("/getClientInfo")
    public Result getClientInfo(@LoginUser SysUser sysUser, @RequestParam("param1") String param1, @RequestParam("param2") String param2) {
        return Result.success().setMessage("success");
    }

    /**
     * 参数数组
     *
     * @param ids
     * @return Result
     */
    @PostMapping("/post")
    public Result closePayDays(@RequestBody String[] ids) {
        return Result.success().setMessage("success");
    }

    /**
     * 参数为对象
     *
     * @param paramRequest
     * @return Result
     */
    @PostMapping("/requestBody")
    public Result requestBody(@RequestBody ParamRequest paramRequest) {
        return Result.success().setMessage("success");
    }

    /**
     * 获取url上数据的 GetMapping/DeleteMapping
     *
     * @param id
     * @return
     */
    @GetMapping("/semotionDelete/{id}")
    @ApiOperation(value = "凭证与自动规则配置-自动认领规则配置-删除", notes = "凭证与自动规则配置-自动认领规则配置-删除")
    public Result deleteSemotionData(@ApiParam(value="类型",type = "long") @PathVariable("id") long id) {
        return Result.success().setMessage("success");
    }
}
