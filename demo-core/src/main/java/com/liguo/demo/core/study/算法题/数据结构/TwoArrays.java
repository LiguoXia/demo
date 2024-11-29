package com.liguo.demo.core.study.算法题.数据结构;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/12 8:39
 * @since 0.0.1
 */
public class TwoArrays {

    public static void main(String[] args) {
        //第一种方式：
        int a[][] = {{1, 2, 3}, {4, 5, 6}};
        //第二种方式；
        int[][] ints = new int[4][2];
        // ints[i][j] = __; //分别赋值
        //第三种方式：第二维的长度可以动态申请
        int[][] arr3 = new int[5][];//五行的长度
        for (int i = 0; i < arr3.length; ++i) {
            arr3[i] = new int[i + 1];   //列的长度每次都变化。每次都要重新申请空间(长度)
            for (int j = 0; j < arr3[i].length; ++j)
                arr3[i][j] = i + j;
        }
        // 遍历二维数组
        for (int i = 0; i < a.length; i++) { // 循环遍历行
            for (int j = 0; j < a[i].length; j++) { // 循环遍历列
                System.out.print(a[i][j] + " ");
            }
            System.out.println(); // 在每行结束后换行
        }
    }
}
