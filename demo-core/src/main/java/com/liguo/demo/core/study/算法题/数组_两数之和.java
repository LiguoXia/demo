package com.liguo.demo.core.study.算法题;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * <p>
 * 你可以按任意顺序返回答案。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 * <p>
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 * <p>
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/11 21:22
 * @since 0.0.1
 */
@Slf4j
public class 数组_两数之和 {

    static int[] arr = new int[2];

    public static int[] twoSum(int[] nums, int target) {
        // 创建哈希表，用于保存数字及其索引
        HashMap<Integer, Integer> map = new HashMap<>();

        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // 检查哈希表中是否存在 complement
            if (map.containsKey(complement)) {
                // 返回满足条件的两个数的索引
                return new int[]{map.get(complement), i};
            }

            // 将当前数字及其索引放入哈希表
            map.put(nums[i], i);
        }

        // 如果没有找到满足条件的两个数，返回空数组
        return new int[0];
    }

    public static void main(String[] args) {
        log.info("---:{}", arr[0]);
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);

        System.out.println("输出：" + result[0] + ", " + result[1]);
    }
}
