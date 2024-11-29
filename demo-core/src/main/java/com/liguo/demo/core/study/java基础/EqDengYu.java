package com.liguo.demo.core.study.java基础;

import lombok.extern.slf4j.Slf4j;

/**
 * byte      1      (byte)0         Byte
 * short     2      (short)0        Short
 * int       4      0               Integer
 * long      8      0L              Long
 * float     4      0.0f            Float
 * double    8      0.0d            Double
 * boolean   -      false           Boolean
 * char      2      \u0000(null)    Character
 *
 * ==
 * 比较变量的值是否相同。
 * 如果比较的对象是基本数据类型，则比较数值是否相等；
 * 如果比较的是引用数据类型，则比较的是对象的内存地址是否相等。
 *
 * 因为Java只有值传递，对于==来说，不管是比较基本数据类型，还是引用数据类型的变量，其比较的都是值，只是引用类型变量存的值是对象的地址。
 * 引用类型对象变量其实是一个引用，它们的值是指向对象所在的内存地址。
 *
 * equals方法
 * equals()方法存在于Object类中，而Object类是所有类的父类。在Object类中定义了equals方法：
 * @see Object#equals(Object)
 *
 * 如果类未重写equals方法
 *     调用equals时，会调用Object中的equals方法（实际使用的也是==操作符）
 * 如果类重写了equals方法
 *     调用equals时，会调用该类自己的equals方法（一般是比较对象的内容是否相同）。比如：
 *         String：比较字符串内容是否相同；
 *         Integer：比较对应的基本数据类型int的值是否相同。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 13:21
 * @since 0.0.1
 */
@Slf4j
public class EqDengYu {

    static char aChar;

    public static void main(String[] args) {
        log.info("aChar :[{}]", aChar); // aChar :[ ]
        intDengYu();
    }

    /**
     * Integer -128 127使用缓存
     * @see Integer#valueOf(int)
     */
    public static void intDengYu() {
        Integer i1 = 100;
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        int i5 = 100;
        int i6 = 200;
        System.out.println(i1 == i2); // true
        System.out.println(i3 == i4); // false
        System.out.println(i1.equals(i2)); // true
        System.out.println(i3.equals(i4)); // false
        System.out.println(i1 == i5); // true
        System.out.println(i3 == i6); // true

        StringBuilder sb = new StringBuilder("123");
        sb.append("h");
        log.info("sb:{}", sb);
        StringBuffer sbb = new StringBuffer("123");
        sbb.append("h");
        log.info("sbb:{}", sbb);

    }

}
