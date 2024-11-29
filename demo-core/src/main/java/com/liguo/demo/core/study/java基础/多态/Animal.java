package com.liguo.demo.core.study.java基础.多态;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/17 11:11
 * @since 0.0.1
 */
// 如果一个类不希望任何其他类继承自它，那么可以把这个类本身标记为final。用final修饰的类不能被继承：
// public final class Animal {
public class Animal {
    protected String animalField = "animal";

    void eat() {
        System.out.println("animal eat");
    }

    // 如果一个父类不允许子类对它的某个方法进行覆写，可以把该方法标记为final。用final修饰的方法不能被Override
    public final String hello() {
        return "Hello";
    }
}
