package com.liguo.demo.core.study.interface4Param.polymorphism;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/17 10:26
 * @since 0.0.1
 */
public class Main {
    public static void main(String[] args) {
        AnimalSoundMaker soundMaker = new AnimalSoundMaker();
        soundMaker.makeAnimalSound(new Dog());
        soundMaker.makeAnimalSound(new Cat());
    }
}
