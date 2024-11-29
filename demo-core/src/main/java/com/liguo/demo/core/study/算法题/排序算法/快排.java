package com.liguo.demo.core.study.算法题.排序算法;

import java.util.Arrays;

/**
 *
 快速排序（QuickSort）是一种基于分治思想的排序算法。它的基本思路是选择一个基准元素，将数组分成两个子数组，使得小于基准的元素都在基准的左边，大于基准的元素都在基准的右边，然后对这两个子数组递归地进行快速排序。

 下面是快速排序的详细步骤：

 选择基准元素： 从数组中选择一个元素作为基准。通常可以选择第一个元素、最后一个元素或者随机选择。

 分区过程： 将数组中小于基准的元素移到基准的左边，大于基准的元素移到基准的右边。这一过程称为分区（Partition）。可以使用两个指针，一个从数组的左边开始，一个从右边开始，逐步移动指针直到它们相遇。

 从左边找到第一个大于基准的元素。
 从右边找到第一个小于基准的元素。
 交换这两个元素。
 这样，所有小于基准的元素都在左边，大于基准的元素都在右边。

 递归排序： 对基准左边和右边的子数组进行递归排序。重复以上步骤直到子数组的大小为1。

 合并结果： 递归的最终结果就是整个数组有序。
 *
 * 平均时间复杂度为O(n log n)
 * 最坏的情况下，即每次选择的枢轴都是当前子数组中的最小或最大元素时，快速排序的时间复杂度会变为O(n^2)。
 * 最好的情况下，即每次选择的枢轴都能将数组均匀地分成两半，快速排序的时间复杂度为O(n log n)。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/7 21:00
 * @since 0.0.1
 */
public class 快排 {
    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // 分区操作，得到基准元素的位置
            int pivotIndex = partition(array, low, high);

            // 递归对基准左右两侧的子数组进行排序
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        // 选择最后一个元素作为基准
        int pivot = array[high];
        int i = low - 1; // 指向小于基准的元素的位置

        // 遍历数组，将小于基准的元素移到基准的左边
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                // 交换元素
                swap(array, i, j);
            }
        }

        // 将基准元素放到正确的位置
        swap(array, i + 1, high);

        // 返回基准元素的位置
        return i + 1;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {12, 4, 5, 6, 7, 3, 1, 15};
        int n = array.length;

        System.out.println("Original Array: " + Arrays.toString(array));

        quickSort(array, 0, n - 1);

        System.out.println("Sorted Array: " + Arrays.toString(array));
    }
}
