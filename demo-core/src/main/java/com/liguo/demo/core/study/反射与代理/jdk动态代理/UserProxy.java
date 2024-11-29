package com.liguo.demo.core.study.反射与代理.jdk动态代理;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 *
 * 自定义类加载器通常在以下情况下被用到：
 *
 * 定制加载策略： 当你希望改变类的加载方式，实现一些特定的加载策略时，可以自定义类加载器。例如，你可能希望从数据库、网络或其他非标准的位置加载类文件。
 *
 * 隔离命名空间： 如果你需要在同一个Java虚拟机中加载两个版本不同的同名类，或者避免类的版本冲突，你可以使用自定义类加载器来创建一个隔离的命名空间。
 *
 * 动态生成类： 在运行时动态生成类的情况下，你可以实现自定义类加载器来加载这些动态生成的类。
 *
 * 安全性控制： 自定义类加载器可以用于实施一些额外的安全性控制。例如，只允许特定代码来源的类被加载。
 *
 * 字节码加密或解密： 在一些安全性要求较高的场景，可以使用自定义类加载器来实现对字节码的加密和解密，以保护代码的安全性。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 21:49
 * @since 0.0.1
 */
@Slf4j
public class UserProxy implements InvocationHandler {

    // 被代理类
    private Object target;

    public UserProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // log.info("invoke第一个对象参数是{}", proxy.getClass().getName());

        Object res = method.invoke(target, args);
        log.info("记录日志1");
        return res;
    }
}
