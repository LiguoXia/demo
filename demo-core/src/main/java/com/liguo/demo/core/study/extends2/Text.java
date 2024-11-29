package com.liguo.demo.core.study.extends2;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/6/1 22:10
 * @since 0.0.1
 */
public class Text {
    public static void main(String[] args) {
        Car car = new Car();
        Bus bus = new Bus();
        bus.age = 1;
        bus.name = "liguo";
        hh(bus);
    }

    public static void hh (CarBase carBase) {
        carBase.say();
    }
}
