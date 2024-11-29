package com.liguo.demo.core.study.java基础.多态;

/**
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1260455778791232
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/17 11:16
 * @since 0.0.1
 */
public class Test {
    public static void main(String[] args) {
        // 向上转型
        Animal a = new Cat();
        a.animalField = "cat";
        System.out.println(a.animalField);
        a.eat();

        A aa = new A() {
            @Override
            public void eat() {
                System.out.println("12");
            }
        };
        test(aa);

        // 向下转型
        Cat c = (Cat) a;
        c.run();
        System.out.println(c.animalField);
    }

    public static void test(A a) {
        a.eat();
    }
}
