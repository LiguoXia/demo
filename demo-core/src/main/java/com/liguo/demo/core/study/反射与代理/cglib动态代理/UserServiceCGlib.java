package com.liguo.demo.core.study.反射与代理.cglib动态代理;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 22:48
 * @since 0.0.1
 */
public class UserServiceCGlib implements MethodInterceptor {

    // 被代理对象
    private Object target;

    // 有参构造方法
    public UserServiceCGlib(Object target) {
        this.target = target;
    }

    //返回一个代理对象:    是 target对象的代理对象
    public Object getProxyInstance() {
        //1. 创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2. 设置父类
        enhancer.setSuperclass(target.getClass());
        //3. 设置回调函数
        enhancer.setCallback(this);
        //4. 创建子类对象，即代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("增强开始");
        Object result = proxy.invoke(target, args);
        // 这也可以执行代理类方法
        // method.invoke(target, args);
        System.out.println("增强结束");
        return result;
    }
}
