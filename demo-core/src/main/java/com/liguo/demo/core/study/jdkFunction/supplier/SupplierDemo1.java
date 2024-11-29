package com.liguo.demo.core.study.jdkFunction.supplier;

import java.util.function.Supplier;

/**
 * <pre>
 * tag：Supplier
 * 它被设计用来代表一个生产者或提供者，无需输入参数就可以返回一个结果。
 * 无参数：Supplier 接口的 get() 方法不接受任何参数。
 * 有返回值：Supplier 的主要任务是提供一个值（生产值）。
 * 函数式接口：可用 Lambda 表达式或方法引用来实现。
 *
 * 适用场景
 * 1、延迟计算：在需要时再计算或生成值。
 * 2、惰性初始化：只有在某些条件下才执行生成逻辑。
 * 3、工厂模式：用于提供对象的工厂方法。
 * 4、配置值生成：动态生成配置值，例如随机数、当前时间等。
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/27 22:08
 * @since 0.0.1
 */
public class SupplierDemo1 {
    public static void main(String[] args) {
        // 方式1 使用 Lambda 表达式创建 Supplier
        Supplier<String> supplier = () -> "Hello, Supplier!";
        System.out.println(supplier.get());

        // 方式2 构造supplier
        String str = supplierMtd(() -> hh("s1"));
    }

    public static String supplierMtd(Supplier<String> supplier) {
        return supplier.get();
    }

    public static String hh(String s1) {
        return "a" + s1;
    }

    public static class GenericExample<T, U> {
        // 当方法中使用了泛型（无论是参数还是返回值），并且这些泛型不属于类的泛型范围，就必须在返回类型前声明泛型类型。
        public <T> U echo(T input) {

            return (U) input;
        }

        public U echo(String input) {

            return (U) input;
        }
    }
}

