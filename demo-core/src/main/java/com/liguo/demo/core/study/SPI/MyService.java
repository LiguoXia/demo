package com.liguo.demo.core.study.SPI;

/**
 * SPI 即Service Provider Interface ，字面意思就是：“服务提供者的接口”
 *
 * SpringBoot 定义了一套接口规范，这套规范规定：SpringBoot 在启动时会扫描外部引用 jar 包中的META-INF/spring.factories文件
 * 将文件中配置的类型信息加载到 Spring 容器（此处涉及到 JVM 类加载机制与 Spring 的容器知识），
 * 并执行类中定义的各种操作。对于外部 jar 来说，只需要按照 SpringBoot 定义的标准，就能将自己的功能装置进 SpringBoot。
 * 自 Spring Boot 3.0 开始，自动配置包的路径从META-INF/spring.factories
 * 修改为 META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports。
 *
 * https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.7-Release-Notes#new-autoconfiguration-annotation

 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/22 11:22
 * @since 0.0.1
 */
public interface MyService {
    void doSomething();
}
