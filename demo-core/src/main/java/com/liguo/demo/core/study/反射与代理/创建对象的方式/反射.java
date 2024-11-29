package com.liguo.demo.core.study.反射与代理.创建对象的方式;

import java.lang.reflect.InvocationTargetException;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/17 16:06
 * @since 0.0.1
 */
public class 反射 {
    private String name;

    public 反射(String name) {
        this.name = name;
    }

    private void printName() {
        System.out.println("Name: " + name);
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 创建对象
        Class<?> clazz = 反射.class;
        反射 anotherObj = (反射) clazz.getDeclaredConstructor(String.class).newInstance("Example");
        anotherObj.printName();
    }
}
