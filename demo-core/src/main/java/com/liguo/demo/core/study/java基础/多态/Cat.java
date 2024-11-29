package com.liguo.demo.core.study.java基础.多态;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/17 11:14
 * @since 0.0.1
 */
public class Cat extends Animal{
    @Override
    void eat() {
        System.out.println("cat eat");
    }

    void run() {
        System.out.println("cat run");
    }
}
