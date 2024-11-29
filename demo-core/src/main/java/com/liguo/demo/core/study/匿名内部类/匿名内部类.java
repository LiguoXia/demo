package com.liguo.demo.core.study.匿名内部类;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/12 20:15
 * @since 0.0.1
 */
@Slf4j
public class 匿名内部类 {
    public static void main(String[] args) {
        // 匿名内部类的写法通常涉及创建一个没有显式名称的类实例，并直接在实例后面使用花括号来定义类的主体
        // 这通常发生在实现接口或继承抽象类的情况下
        Greeting greeting = new Greeting(){
            /**
             * 问候
             */
            @Override
            public void greet() {
                log.info("hello world");
            }
        };

        greeting.greet();
    }
}
