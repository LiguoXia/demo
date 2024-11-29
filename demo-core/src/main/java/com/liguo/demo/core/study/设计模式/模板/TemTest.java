package com.liguo.demo.core.study.设计模式.模板;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/30 17:05
 * @since 0.0.1
 */
public class TemTest {

    public static void main(String[] args) {
        TmpParent tmpParent = new Son1TmpParent();
        tmpParent.refresh();
    }

    static{
        hello(TemTest::setHello);
    }

    static void setHello() {
        System.out.println("123");
    }

    static void hello(Runnable runnable) {
        runnable.run();
    }
}
