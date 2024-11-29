package com.liguo.demo.core.study.functionalInterface;

/**
 * <pre>
 * tag：
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/19 17:07
 * @since 0.0.1
 */
public class FunctionalInterfaceTest {


    public static void main(String[] args) {
        test("1");
    }

    public static void test(String a) {
        a = "a" + a;
        String finalA = a;
        // finalA能作为参数：
        // 在 lambda 表达式中，Java 会捕获作用域中的变量，使这些变量可以在 lambda 表达式中访问。
        // 这被称为 变量捕获，分为两种情况
        // 1 局部变量：lambda 可以捕获方法体中的 final 或 有效 final（即没有被重新赋值）的局部变量。
        // 2 成员变量：lambda 表达式可以直接访问类的成员变量（没有 final 限制）。
        getSingleton(a, b -> createBean(finalA, b));
    }

    public static void getSingleton(String str, FunctionalInterface1<FunctionalInterfaceObj> interface1) {
        System.out.println("step1");
        // getObject参数对应的b的值
        FunctionalInterfaceObj obj = interface1.getObject(str);
        System.out.println("step3");
        obj.hello();
    }

    public static FunctionalInterfaceObj createBean(String a2, String a3) {
        System.out.println("step2");
        System.out.println("a2:" + a2);
        System.out.println("a3:" + a3);
        return new FunctionalInterfaceObj();
    }
}
