package com.liguo.demo.core.study.abstractClass;

/**
 * 抽象类不能被实例化，必须由子类继承并实现抽象类中的抽象方法。
 * <p>一般抽象类用于模板方法模式,多个类通用的逻辑写在抽象类的实例方法,个性化方法定义为抽象方法,有子类去实现
 * <p>子类继承父类如果不复写父类的抽象方法，要想不出错，这个子类也必须是抽象类
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/22 22:55
 * @since 0.0.1
 */
public abstract class AbstractAnimal {
    /**
     * 抽象方法,有子类去实现
     */
    public abstract void sound();

    /**
     * 实例方法,子类可以直接继承或重写
     */
    public void eatAndSound() {
        System.out.println("This animal is eating.");
        // 通用方法
        sound();
    }
}
