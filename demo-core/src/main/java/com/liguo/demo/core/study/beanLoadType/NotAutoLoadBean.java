package com.liguo.demo.core.study.beanLoadType;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/25 16:54
 * @since 0.0.1
 */
@Slf4j
public class NotAutoLoadBean {

    public String sayHello() {
        log.info("hello");
        return "hello";
    }
}
