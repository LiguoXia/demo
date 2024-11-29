package com.liguo.demo.core.study.classloader.case1;

import java.lang.reflect.Method;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/29 11:24
 * @since 0.0.1
 */
public class TestLoad {
    public static void main(String[] args) {
        try {
            // 创建自定义类加载器，指定类文件所在目录
            MyClassLoader myClassLoader = new MyClassLoader();
            MyClassLoader.classMap.put("com.liguo.demo.core.study.classloader.case1.NeedLoadClass1", "D:/class/NeedLoadClass1.class");

            // 加载指定类（假设类名为 com.example.MyClass）
            Class<?> clazz = myClassLoader.loadClass("com.liguo.demo.core.study.classloader.case1.NeedLoadClass1");

            // 输出类加载器
            System.out.println("Class loaded by: " + clazz.getClassLoader());

            // 实例化类并调用方法（假设该类有一个无参构造函数）
            Object instance = clazz.getDeclaredConstructor().newInstance();
            instance.toString();
            System.out.println("Instance: " + instance);

            Method sayHello = instance.getClass().getMethod("sayHello");
            sayHello.invoke(instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
