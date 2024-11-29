package com.liguo.demo.core.study.extends2;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/6/1 22:06
 * @since 0.0.1
 */
@Slf4j
public class CarBase {

    int age;
    String name;

    public CarBase() {
        System.out.println("CarBase类构造函数被调用！");
    }
    public CarBase(int age, String name) {
        System.out.println("CarBase类构造函数2被调用！");
    }

    public void say() {
        System.out.println("hello" + age + name);
    }
}
