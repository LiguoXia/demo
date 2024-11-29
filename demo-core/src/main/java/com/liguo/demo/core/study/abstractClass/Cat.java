package com.liguo.demo.core.study.abstractClass;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/22 23:00
 * @since 0.0.1
 */
public class Cat extends AbstractAnimal{

    /**
     * 抽象方法,有子类去实现
     */
    @Override
    public void sound() {
        System.out.println("The cat meows");
    }
}
