package com.liguo.demo.consume.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * dsc
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
        log.info("监听器,可以干预springboot启动流程,当前事件:{}", event.getClass());
    }
}
