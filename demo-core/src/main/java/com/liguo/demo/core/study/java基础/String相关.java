package com.liguo.demo.core.study.java基础;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 14:18
 * @since 0.0.1
 */
public class String相关 {

    public static void main(String[] args) {
        String a = "\uD840\uDC0B";
        System.out.println(a);
        //str();
        printUnicode("夏利国Aa");
    }

    /**
     * String
     *     String是只读字符串，它并不是基本数据类型，而是一个对象。从底层源码来看是一个final类型的
     *     字符数组，所引用的字符串不能被改变，一经定义，无法再增删改。每次对String的操作都会生成
     *     新的String对象。
     *     private final char value[]
     *
     *     每次+操作 ： 隐式在堆上new了一个跟原字符串相同的StringBuilder对象，再调用append方法 拼
     *     接+后面的字符。
     *
     * StringBuffer
     *     对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。
     *     StringBuilder 并没有对方法进行加同步锁，所以是非线程安全的。
     *
     *
     */
    public static void str() {
        // 初始 char数组容量为 初始话字符个数 + 16
        StringBuilder sb = new StringBuilder("夏");
        sb.append("你");

        StringBuffer stringBuffer = new StringBuffer("你");
        stringBuffer.append("爱我");
    }

    public static void printUnicode(String text) {

        System.out.println("字符串: " + text);
        System.out.println("字符的 Unicode 编码: ");

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            int codePoint = text.codePointAt(i);

            // 如果当前字符是高代理且下一个字符是低代理，则跳过下一个字符
            if (Character.isHighSurrogate(ch) && i + 1 < text.length() && Character.isLowSurrogate(text.charAt(i + 1))) {
                i++; // 跳过低代理
            }

            System.out.printf("字符: %s, Unicode: \\u%04X%n", new String(Character.toChars(codePoint)), codePoint);
        }
    }
}
