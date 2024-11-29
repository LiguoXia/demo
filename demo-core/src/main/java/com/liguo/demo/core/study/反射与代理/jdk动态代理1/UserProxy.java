package com.liguo.demo.core.study.反射与代理.jdk动态代理1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 21:49
 * @since 0.0.1
 */
public class UserProxy implements InvocationHandler {

    // 被代理类
    private Object target;

    public UserProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res = method.invoke(target, args);

        System.out.println("记录日志");

        return res;
    }
}
