package com.liguo.demo.core.study.IO模型.Buffer;

import java.nio.IntBuffer;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/18 19:30
 * @since 0.0.1
 */
public class IntBufferTest1 {
    public static void main(String[] args) {
        allocateTest();
        putTest();
        flipTest();
    }

    static IntBuffer intBuffer = null;

    public static void allocateTest() {
        // 创建了一个Intbuffer实例对象
        intBuffer = IntBuffer.allocate(20);
        System.out.println("------------after allocate------------------");
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());
    }

    public static void putTest() {
        for (int i = 0; i < 5; i++) {
            //写入一个整数到缓冲区
            intBuffer.put(i);
        }

        //输出缓冲区的主要属性值
        System.out.println("------------after putTest------------------");
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());
    }

    public static void flipTest()
    {
        //翻转缓冲区，从写入模式翻转成读取模式
        // 现在还处于读取模式，我们必须调用Buffer.clear()或Buffer.compact()方法，即清空或者压缩缓冲区，将缓冲区切换成写入模式，让其重新可写
        intBuffer.flip();
        //输出缓冲区的主要属性值
        System.out.println("------------after flip ------------------");
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());
    }
}
