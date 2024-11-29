package com.liguo.demo.core.study.classloader;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/29 10:47
 * @since 0.0.1
 */
public class UsedClass {
    private static int a = 12;

    public UsedClass(int a) {
        this.a = a;
    }

    public UsedClass() {

    }

    public String sayHello(String name) {
        return "helle" + name;
    }

}
