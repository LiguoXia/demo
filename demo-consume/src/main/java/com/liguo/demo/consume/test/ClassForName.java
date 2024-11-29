package com.liguo.demo.consume.test;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/18 10:11
 * @since 0.0.1
 */
public class ClassForName {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<Dog> cls3 = (Class<Dog>) Class.forName("com.liguo.demo.consume.test.Dog");

        Class<?> cls1 = Class.forName("com.liguo.demo.consume.test.Dog");

        System.out.println(cls1 == cls3);

    }
}
