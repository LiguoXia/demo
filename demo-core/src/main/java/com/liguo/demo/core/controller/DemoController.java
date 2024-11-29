package com.liguo.demo.core.controller;

import com.liguo.demo.core.pojo.entity.Demo;
import com.liguo.demo.core.service.DemoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import java.util.List;

/**
 * Demo接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/29 16:42
 * @since 0.0.1
 */
@Api(tags = "Demo接口")
@Slf4j
@RestController
@RequestMapping("/api/demo")
public class DemoController extends HttpServlet {
    @Autowired
    private DemoService demoService;

    @ApiOperation("列表查询")
    @GetMapping("/list")
    public List<Demo> list() {
        return demoService.findAll();
    }

    @ApiOperation("验证mybatis一级缓存")
    @GetMapping("/{id}")
    public Demo getById(@PathVariable int id) {
        return demoService.findById(id);
    }

    /**
     * 缓存的查询顺序：
     * <p>二级缓存 -> 一级缓存 -> 数据库
     * <p>1、配置文件开启：mybatis.configuration.cache-enabled=true
     * <p>2、xml文件加上<cache eviction="LRU" flushInterval="100000" readOnly="true" size="1024"></cache>
     *
     * <p><cache/>和 mapper类@CacheNamespace效果一致,但是作用范围不一致,@CacheNamespace是真读写在类上的sql,</cache>是xml的sql
     * <p>需要使用<cache-ref namespace=""/> 来引用关联
     *
     * @param id
     * @return
     */
    @ApiOperation("验证mybatis二级缓存")
    @GetMapping("/getByIdCache/{id}")
    public Demo getByIdCache(@PathVariable int id) {
        return demoService.findByIdCache2(id);
    }

    @ApiOperation("新增")
    @PostMapping("/add")
    public void add(@RequestBody Demo demo) {
        demoService.save(demo);
    }

    @ApiOperation("更新")
    @PutMapping("/update")
    public void update(@RequestBody Demo demo) {
        demoService.update(demo);
    }

    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        demoService.deleteById(id);
    }
}
