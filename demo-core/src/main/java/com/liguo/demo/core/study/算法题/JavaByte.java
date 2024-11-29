package com.liguo.demo.core.study.算法题;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Unicode 码点的最长表示是 4 个字节。Unicode 码点是用来标识字符的唯一整数值，通常用 U+ 加上一个十六进制数字表示。
 *
 * Unicode 码点的范围是 U+0000 到 U+10FFFF。在 UTF-8 编码中，可以使用 1 到 4 个字节来表示一个 Unicode 码点。
 *
 * 对于 U+0000 到 U+007F 的码点，使用一个字节。
 * 对于 U+0080 到 U+07FF 的码点，使用两个字节。
 * 对于 U+0800 到 U+FFFF 的码点，使用三个字节。
 * 对于 U+10000 到 U+10FFFF 的码点，使用四个字节。
 * 需要注意的是，并不是所有的 Unicode 码点都被分配和使用，因此并不是所有的码点都会在实际的文本中出现。但为了兼容 Unicode 的整体设计，UTF-8 提供了这种灵活的编码方式。
 *
 * Unicode 是一种通用字符集，本质上是给每个字符分配一个编号（称为“码点”），但它并没有规定在计算机中如何存储这些字符码点。
 *
 * Java 默认的字符集（编码）取决于运行程序的环境。通常情况下，它是与操作系统相关的默认字符集。
 * UTF-16 编码：使用 2 或 4 字节来表示一个字符。所有的 ASCII 字符和常用的非英文字符，都用 2 字节表示；
 * 少数字符需要用到 4 字节表示。对于 2 字节的字符，UTF-16 编码与 Unicode 码点相等。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/15 11:55
 * @since 0.0.1
 */
@Slf4j
public class JavaByte {
    public static void main(String[] args) {
        //String myString = "你好,世界"; // 一个包含中文字符的例子
        String myString = "你好"; // 一个包含中文字符的例子
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("默认字符集：" + defaultCharset.displayName());

        // 遍历字符串中的每个字符
        for (char c : myString.toCharArray()) {
            // 将字符的 Unicode 编码转换为二进制表示
            String binaryRepresentation = Integer.toBinaryString(c);

            // 打印字符和其二进制表示
            // Unicode 是一种通用字符集，本质上是给每个字符分配一个编号（称为“码点”），但它并没有规定在计算机中如何存储这些字符码点。
            // 这里对应的是Unicode的二进制码点 https://www.lddgo.net/string/unicode-chart?format=hex&start=4e00&end=9fff
            System.out.println("字符: " + c + ", 二进制编码: " + binaryRepresentation);
        }

        try {
            // 使用默认字符编码输出字节数 UTF-16
            byte[] defaultBytes = myString.getBytes();
            System.out.println("字节数（默认编码）：" + defaultBytes.length);
            for (byte b : defaultBytes) {
                log.info("b:{}", b); // 以补码的形式算出对应的整形
                String binaryRepresentation = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
                log.info("二进制:{}", binaryRepresentation);
            }


            // 使用指定字符编码（UTF-8）输出字节数
            byte[] utf16Bytes = myString.getBytes("UTF-16");
            System.out.println("字节数（UTF-16 编码）：" + utf16Bytes.length);

            // 使用指定字符编码（UTF-8）输出字节数
            byte[] utf8Bytes = myString.getBytes("UTF-8");
            System.out.println("字节数（UTF-8 编码）：" + utf8Bytes.length);

            // 使用指定字符编码（GBK）输出字节数
            byte[] gbkBytes = myString.getBytes("GBK");
            System.out.println("字节数（GBK 编码）：" + gbkBytes.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
