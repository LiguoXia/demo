package com.liguo.demo.core.study.abstractClass;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/22 23:00
 * @since 0.0.1
 */
public class Text {
    public static void main(String[] args) {
        // Animal animal = new Animal(); // 不能直接实例化抽象类

        AbstractAnimal dog = new Dog();  // 实例化子类
        AbstractAnimal cat = new Cat();

        dog.sound();  // 输出：The dog barks
        cat.sound();  // 输出：The cat meows

        dog.eatAndSound();    // 输出：This animal is eating.
        cat.eatAndSound();    // 输出：This animal is eating.
    }
}
