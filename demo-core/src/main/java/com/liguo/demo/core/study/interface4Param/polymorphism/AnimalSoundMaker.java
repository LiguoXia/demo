package com.liguo.demo.core.study.interface4Param.polymorphism;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/17 10:26
 * @since 0.0.1
 */
public class AnimalSoundMaker {
    public void makeAnimalSound(Animal animal) {
        animal.makeSound();
    }
}
