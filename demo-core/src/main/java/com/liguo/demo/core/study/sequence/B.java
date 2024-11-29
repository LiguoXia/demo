package com.liguo.demo.core.study.sequence;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/24 17:16
 * @since 0.0.1
 */
public class B {
    public static void b1() {
        C.c2();
        b2();
    }

    public static void b2() {
        for (int i = 0; i < 10; i++) {
            C.c1();
        }
    }
}
