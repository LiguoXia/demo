package com.liguo.demo.core.study.算法题.整数;

/**
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 例如，121 是回文，而 123 不是。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 121
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 * <p>
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/14 21:16
 * @since 0.0.1
 */
public class 判断是否回文 {
    public static boolean isPalindrome(int x) {
        // 负数和0结尾的整数不是回文整数
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int revertedNumber = 0;
        // 对半比较, 循环退出条件: 剩余整数 < revertedNumber
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x = x / 10;
        }

        return x == revertedNumber /10 || x == revertedNumber;
    }

    public static void main(String[] args) {
        int num = 123454321;
        boolean palindromeFlag = isPalindrome(num);
        System.out.println("number:" + num + (palindromeFlag ? "是" : "不是") + "回文整数");
    }
}
