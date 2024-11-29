package com.liguo.demo.consume.controller;

import com.liguo.demo.consume.pojo.entity.Demo;
import com.liguo.demo.consume.service.DemoService;
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

    @ApiOperation("根据ID查询")
    @GetMapping("/{id}")
    public Demo getById(@PathVariable int id) {
        return demoService.findById(id);
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
