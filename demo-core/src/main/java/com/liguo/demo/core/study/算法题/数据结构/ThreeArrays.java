package com.liguo.demo.core.study.算法题.数据结构;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/12 9:00
 * @since 0.0.1
 */
public class ThreeArrays {
    public static void main(String[] args) {
        // 声明并初始化一个2x3x4的整数类型三维数组
        int[][][] myArray = {
                {
                        {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}
                },
                {
                        {13, 14, 15, 16}, {17, 18, 19, 20}, {21, 22, 23, 24}
                }
        };

        // 遍历三维数组
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 4; k++) {
                    System.out.print(myArray[i][j][k] + " ");
                }
                System.out.println(); // 在每个二维数组的行结束后换行
            }
            System.out.println(); // 在每个一维数组的结束后换行
        }
    }
}
