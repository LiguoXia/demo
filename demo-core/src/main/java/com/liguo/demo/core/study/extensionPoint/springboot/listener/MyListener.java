package com.liguo.demo.core.study.extensionPoint.springboot.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationFailedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

/**
 * 内置事件类型
 * @see ContextRefreshedEvent 当 ApplicationContext 初始化或刷新时触发。
 * @see ContextStartedEvent 当 ApplicationContext 启动时触发。
 * @see ContextStoppedEvent 当 ApplicationContext 停止时触发。
 * @see ContextClosedEvent 当 ApplicationContext 关闭时触发。
 * @see ApplicationReadyEvent 当 Spring Boot 应用完全启动后触发。
 * @see ApplicationFailedEvent 当 Spring Boot 启动失败时触发。
 *
 * 多个容器也会执行多次
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/10 16:58
 * @since 0.0.1
 */
@Slf4j
public class MyListener implements ApplicationListener {

    /**
     * 监听某一个事件, 实现类上泛型加上某个事件
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // log.info("监听器,可以干预springboot启动流程,当前事件:{}", event.getClass());
    }
}
