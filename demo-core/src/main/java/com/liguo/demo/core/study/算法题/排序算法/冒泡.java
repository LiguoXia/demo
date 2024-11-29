package com.liguo.demo.core.study.算法题.排序算法;

import java.util.Arrays;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/7 21:33
 * @since 0.0.1
 */
public class 冒泡 {
    public static void bubbleSort(int[] array) {
        int n = array.length;

        // 外层循环控制遍历的轮数
        for (int i = 0; i < n - 1; i++) {
            // 内层循环控制每一轮的比较和交换
            // 注意：每一轮结束后，数组的最后一个元素已经是最大的，所以下一轮比较时可以减少一个元素
            for (int j = 0; j < n - 1 - i; j++) {
                // 比较相邻的两个元素，如果前面的大于后面的，则交换它们
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1);
                }
            }
        }
    }

    private static void swap(int[] array, int i, int j) {
        // 交换数组中的两个元素的位置
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {12, 4, 5, 6, 7, 3, 1, 15};

        System.out.println("Original Array: " + Arrays.toString(array));

        bubbleSort(array);

        System.out.println("Sorted Array: " + Arrays.toString(array));
    }
}
