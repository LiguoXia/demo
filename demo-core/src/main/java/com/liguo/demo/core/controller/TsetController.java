package com.liguo.demo.core.controller;

import com.alibaba.druid.pool.DruidDataSource;
import com.liguo.demo.core.enums.TrafficCodeEnum;
import com.liguo.demo.core.factory.TrafficModeFactory;
import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.service.IMailService;
import com.liguo.demo.core.service.RocketMqHelper;
import com.liguo.demo.core.service.ThreadPoolTestService;
import com.liguo.demo.core.service.TrafficModeService;
import com.liguo.demo.core.service.superman.ClientTest;
import com.liguo.demo.core.study.jdbctemplate.JdbcTemplateTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;

@Api(tags = "测试接口")
@RestController
@RequestMapping("/aa/bb")
@Slf4j
public class TsetController {
    @Autowired
    private IMailService iMailService;
    @Autowired
    private ThreadPoolTestService poolTestService;
    @Autowired
    private JdbcTemplateTest jdbcTemplateTest;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ClientTest clientTest;

    @ApiOperation("邮件发送")
    @PostMapping("/test")
    public Result test(@ApiParam("用户名") @RequestParam("name") String name) {

        log.info("验证fegin调用能否获取request");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 1 获取单个请求头
        String userName = request.getHeader("__sf.beic.USER_NAME__");
        log.info("====获取单个请求头=====");
        log.info("core微服务带过来的请求头:user:{}", userName);

        log.info("请求参数:{}", name);
        // iMailService.sendSimpleMail("xialiguo0212@163.com", "title", "正文：" + name + "你好!");
        return Result.success("Helle:" + name + "这是核心服务");
    }

    @ApiOperation("线程池测试方法")
    @PostMapping("/test2")
    public Result test2(@ApiParam("用户名") @RequestParam("name") String name) {
        log.info("请求参数:{}", name);
        poolTestService.sayHello();
        return Result.success("Helle:" + name + "这是核心服务");
    }

    @ApiOperation("接口存在多个实现类时的动态调用")
    @PostMapping("/test1")
    public Result testImplSelect(@ApiParam("用户名") @RequestParam("name") String name) {
        log.info("请求参数:{}", name);
        TrafficModeService mode = TrafficModeFactory.getTrafficMode(TrafficCodeEnum.BUS);

        return Result.success("Helle:" + name + "这是核心服务,返回:" + mode.getFee());
    }

    @ApiOperation("接口存在多个实现类时的动态调用")
    @PostMapping("/jdbctemplate")
    public Result jdbctemplate() throws SQLException {
        jdbcTemplateTest.getAll();
        //看一下默认数据源
        System.out.println(dataSource.getClass());
        //获得连接
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());

        //关闭连接
        connection.close();
        return Result.success();
    }

    @ApiOperation("接口存在多个实现类时的动态调用")
    @PostMapping("/restTemplate")
    public Result restTemplate()  throws UnsupportedEncodingException {
        clientTest.get("","","");
        return Result.success();
    }

    public static int i = 0;
    @Autowired
    private RocketMqHelper rocketMqHelper;
    @GetMapping("/test1")
    public String test() {
        rocketMqHelper.sendMessage("topic11", "Hello RocketMQ" + (++i));
        return "Message sent!";
    }
}
