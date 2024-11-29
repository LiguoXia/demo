package com.liguo.demo.core.study.java基础.继承.构造函数1;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/15 9:17
 * @since 0.0.1
 */
public class Son1 extends Father1{

    public Son1 () {
        System.out.println("子类构造函数执行");
    }

    public static void main(String[] args) {
        Son1 son1 = new Son1();
    }
}
