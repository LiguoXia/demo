package com.liguo.demo.core.study.锁.qualify;

import com.liguo.demo.core.pojo.entity.Demo;

/**
 * 个类只有一个类对象，但是有很多个实例对象 要确认锁的是类还是锁的是类的实例
 *
 * 如果锁的是类，类的所有实例都会影响
 *
 * 如果锁的是实例，不同实例获取锁不会影响
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/31 10:15
 * @since 0.0.1
 */
public class Qualify {

    //同步方法，对象锁
    public synchronized void dosth() {
        System.out.println("Hello World");
    }

    //同步方法，类锁
    public synchronized static void dosth1() {
        System.out.println("Hello World");
    }

    //同步代码块，类锁
    public void dosth2() {
        synchronized (Demo.class) {
            System.out.println("Hello World");
        }
    }

    //同步代码块，对象锁
    public void dosth3() {
        synchronized (this) {
            System.out.println("HelloWorld");
        }
    }
}
