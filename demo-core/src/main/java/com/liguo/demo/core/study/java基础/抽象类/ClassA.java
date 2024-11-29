package com.liguo.demo.core.study.java基础.抽象类;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/16 22:45
 * @since 0.0.1
 */
@Slf4j
public class ClassA extends AbstractClassA{
    @Override
    void sayHello() {
        log.info("抽象类实现类重写抽象类sayHello");
    }

    public static void main(String[] args) {
        // 抽象类不能实例化, 子类实例指向父类对象的引用
        // 父类类型的引用来引用子类对象
        AbstractClassA a = new ClassA();
        a.method();
    }
}
