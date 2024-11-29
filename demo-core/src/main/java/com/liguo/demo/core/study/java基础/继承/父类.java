package com.liguo.demo.core.study.java基础.继承;

/**
 * 执行顺序的总结：
 * 父类的静态代码块。 静态代码块会在类加载时执行，并且只会执行一次。父类的静态代码块优先于子类的静态代码块执行。
 * 子类的静态代码块。
 * 父类的构造代码块（实例代码块）。构造代码块在每次创建对象时执行。父类的构造代码块优先于子类的构造代码块执行。
 * 父类的构造方法。
 * 子类的构造代码块。构造方法在构造代码块执行完后执行。父类的构造方法优先于子类的构造方法执行。
 * 子类的构造方法。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/16 21:25
 * @since 0.0.1
 */
public class 父类 {

    static {
        System.out.println("父类静态代码块");
    }

    // 构造代码块
    {
        System.out.println("父类的构造代码块");
    }

    public 父类() {
        System.out.println("父类构造方法");
    }

    public String aa(String a) {
        return a + "b";
    }
}
