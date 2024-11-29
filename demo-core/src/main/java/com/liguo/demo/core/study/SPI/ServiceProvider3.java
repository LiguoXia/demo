package com.liguo.demo.core.study.SPI;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/22 11:22
 * @since 0.0.1
 */
public class ServiceProvider3 implements MyService {
    @Override
    public void doSomething() {
        System.out.println("Service Provider 3 is doing something.");
    }
}
