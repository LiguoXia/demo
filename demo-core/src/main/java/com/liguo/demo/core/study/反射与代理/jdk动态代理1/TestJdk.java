package com.liguo.demo.core.study.反射与代理.jdk动态代理1;


import java.lang.reflect.Proxy;

/**
 * JDK动态代理只能代理接口-测试异常情况
 * {@link Proxy} h
 *
 * 没有实现接口，不能使用jdk动态代理
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 21:51
 * @since 0.0.1
 */
public class TestJdk {
    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        UserServiceImpl1 impl = new UserServiceImpl1();
        UserProxy userProxy = new UserProxy(impl);
        UserServiceImpl1 userServiceImpl1 = (UserServiceImpl1) Proxy.newProxyInstance(impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), userProxy);
        userServiceImpl1.addUser();
        userServiceImpl1.updateUser("我是皮皮虾");
    }
}
