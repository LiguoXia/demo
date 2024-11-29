package com.liguo.demo.core.study.锁;

import org.openjdk.jol.info.ClassLayout;

/**
 * -XX:-UseCompressedOops 关闭指针压缩 jdk8版本是默认开启指针压缩的
 * <p>
 * ClassLayout.parseInstance(object).toPrintable()：查看对象内部信息.
 * GraphLayout.parseInstance(object).toPrintable()：查看对象外部信息，包括引用的对象.
 * GraphLayout.parseInstance(object).totalSize()：查看对象总大小.
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/12 21:21
 * @since 0.0.1
 */
public class WatchD {
    public static void main(String[] args) {
        // D d = new D();
        // System.out.println(ClassLayout.parseInstance(d).toPrintable());
        int[] a = {1};
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
