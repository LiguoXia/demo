package com.liguo.demo.core.study.算法题.字符串;

import java.util.Stack;

/**
 * 给定一个字符串，判断字符串可不可以由cm多次插入组成，cm可以插入任意位置，例如，cm、ccmcmm,都可以由cm多次插入组成，请实现算法，判断一个字符串是否可以由cm组成
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/13 9:15
 * @since 0.0.1
 */
public class CMInsertion {

    /**
     * 思路：使用栈, 若下一个字符与栈顶字符组合, 栈顶元素出栈
     *
     * @param input 输入
     * @return 是否
     */
    public static boolean canFormString(String input) {
        if (input == null || input ==  "") {
            return false;
        }
        Stack s = new Stack();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (s.empty() || c == 'c') {
                s.push(c);
                continue;
            }
            if (c == 'm') {
                s.pop();
            }
        }
        return s.empty();
    }

    public static void main(String[] args) {
        String input0 = " ";
        String input1 = "cm";
        String input2 = "ccmcmccmcmmm";
        String input3 = "abc";

        System.out.println(canFormString(input0)); // true
        System.out.println(canFormString(input1)); // true
        System.out.println(canFormString(input2)); // true
        System.out.println(canFormString(input3)); // false
    }
}
