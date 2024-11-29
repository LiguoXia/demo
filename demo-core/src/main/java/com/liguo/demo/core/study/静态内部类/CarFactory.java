package com.liguo.demo.core.study.静态内部类;

/**
 * 工厂模式： 静态内部类可以用于实现工厂模式，封装特定类型对象的创建逻辑。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/12 20:13
 * @since 0.0.1
 */
public class CarFactory {
    // 外部类的工厂实现

    // 静态内部类用于创建轿车
    public static class SedanFactory {
        // 创建轿车的逻辑
    }

    // 静态内部类用于创建SUV
    public static class SUVFactory {
        // 创建SUV的逻辑
    }
}
