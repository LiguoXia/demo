package com.liguo.demo.core.study.sequence;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/24 17:16
 * @since 0.0.1
 */
public class A {

    public static void main(String[] args) {
        a1();
    }

    private static void a1() {
        a3();
        B.b1();
    }

    private static void a3() {
        B.b1();
    }
}
