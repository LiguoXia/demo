package com.liguo.demo.core.study.java基础.抽象类;

import lombok.extern.slf4j.Slf4j;

/**
 * 这样的设计允许在抽象类中提供一些通用的实现，同时通过抽象方法定义了一些需要由子类实现的行为。
 * 这种结构有助于代码的重用和扩展，让继承该抽象类的子类可以定制自己的特定行为，同时利用抽象类提供的共享逻辑。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/16 22:42
 * @since 0.0.1
 */
@Slf4j
public abstract class AbstractClassA {

    public String method() {
        log.info("抽象类实例方法");
        sayHello();
        return "抽象类实例方法返回";
    }

    abstract void sayHello();
}
