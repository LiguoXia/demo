package com.liguo.demo.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * 配置接口
 * <pre>
 * 1. 配置来源顺序（优先级）
 * Spring Boot 使用 SpringApplication 类的 run() 方法来加载配置，配置的加载顺序（从高到低优先级）是：
 *
 * 命令行参数（java -jar yourapp.jar --server.port=8081 --spring.profiles.active=prod）。
 * Java 系统属性（System.getProperties()）。   System.setProperty("server.port", "8082");  System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
 * application.properties 或 application.yml 文件（包括外部的或内部的）。
 * 环境变量（SPRING_APPLICATION_JSON）。
 * 随机数值配置（如 random.*）。
 * @PropertySource 注解的属性。
 * 默认属性（通过 SpringApplication.setDefaultProperties 方法设置的属性）。
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/29 16:42
 * @since 0.0.1
 */
@Api(tags = "配置接口")
@Slf4j
@RestController
@RequestMapping("/properties")
public class PropertiesController {
    @Autowired
    private ConfigurableEnvironment environment;

    @ApiOperation("获取所有配置")
    @PostMapping("/getAllProperties")
    public void getBeanByName() {

        // application属性
        System.out.println("====================================================application属性start=================================================================");
        System.out.println("Active Profiles: " + String.join(", ", environment.getActiveProfiles()));
        System.out.println("Server Port: " + environment.getProperty("server.port"));
        System.out.println("App Name: " + environment.getProperty("spring.application.name"));
        System.out.println("jackson date-format: " + environment.getProperty("spring.jackson.date-format"));
        System.out.println("====================================================application属性end=================================================================");

        // 系统属性,启动参数
        System.out.println("====================================================系统属性start=================================================================");
        for (Map.Entry<String, Object> entry : environment.getSystemProperties().entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        System.out.println("====================================================系统属性end=================================================================");

        System.out.println("====================================================yml属性start=================================================================");
        StreamSupport.stream(environment.getPropertySources().spliterator(), false)
                .filter(propertySource -> propertySource instanceof EnumerablePropertySource)
                .map(propertySource -> ((EnumerablePropertySource<?>) propertySource).getPropertyNames())
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(propertyName ->
                        System.out.println(propertyName + " : " + environment.getProperty(propertyName))
                );
        System.out.println("====================================================yml属性end=================================================================");
    }


}
