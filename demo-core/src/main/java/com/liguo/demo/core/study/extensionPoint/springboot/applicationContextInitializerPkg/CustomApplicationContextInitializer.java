package com.liguo.demo.core.study.extensionPoint.springboot.applicationContextInitializerPkg;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MapPropertySource;

import java.util.Arrays;
import java.util.Collections;
import java.util.stream.StreamSupport;

/**
 * 用途
 * 1、修改上下文配置：可以在上下文启动之前修改 ApplicationContext 的属性。
 * 1、注册 Bean：在上下文初始化时注册一些自定义的 beans。
 * 3、设置环境属性：可以根据不同的环境动态设置属性，例如在开发、测试和生产环境中使用不同的配置。
 * <p>
 * 实现步骤
 * 实现 ApplicationContextInitializer 接口。
 * 在 initialize 方法中实现自定义逻辑。
 * 在 Spring 的配置中注册该 initializer。
 * 1、SpringApplication app = new SpringApplication(MySpringBootApplication.class);
 * app.addInitializers(new CustomApplicationContextInitializer());
 * 2、src/main/resources/META-INF/spring.factories
 * org.springframework.context.ApplicationContextInitializer=\
 * com.example.CustomApplicationContextInitializer
 *
 * springcloud会创建父子容器，这个会被执行多次
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/26 15:19
 * @since 0.0.1
 */
@Slf4j
public class CustomApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    /**
     * Initialize the given application context.
     *
     * @param applicationContext the application to configure
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        // 获取环境对象
        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        // 设置自定义属性
        environment.getPropertySources().addFirst(
                new MapPropertySource("customProps", Collections.singletonMap("custom.property111", "Hello, World!"))
        );

        // 可以添加更多的自定义逻辑，例如注册 beans
        System.out.println("自定义 ApplicationContextInitializer 被调用");


        System.out.println("=== " + applicationContext.getDisplayName() + "All Properties ===");

        StreamSupport.stream(environment.getPropertySources().spliterator(), false)
                .filter(propertySource -> propertySource instanceof EnumerablePropertySource)
                .map(propertySource -> ((EnumerablePropertySource<?>) propertySource).getPropertyNames())
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(propertyName ->
                        System.out.println(propertyName + " : " + environment.getProperty(propertyName))
                );

        System.out.println("=======================");


        // 根据某些条件动态设置激活的 Profile
        String[] Profiles = environment.getActiveProfiles();
        log.info("激活的配置文件:{}", JSON.toJSONString(Profiles));
        if (someCondition()) {
            // environment.addActiveProfile("dev");
        } else {
            // environment.addActiveProfile("prod");
        }
    }

    private boolean someCondition() {
        // 根据实际情况确定条件
        return true;
    }
}
