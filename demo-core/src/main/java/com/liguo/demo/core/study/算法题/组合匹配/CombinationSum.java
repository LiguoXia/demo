package com.liguo.demo.core.study.算法题.组合匹配;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/29 15:14
 * @since 0.0.1
 */
public class CombinationSum {
    public static List<List<BigDecimal>> combinationSum(BigDecimal[] nums, BigDecimal target) {
        List<List<BigDecimal>> result = new ArrayList<>();
        Arrays.sort(nums); // 排序数组，确保组合个数优先最小

        backtrack(result, new ArrayList<>(), nums, target, 0);

        return result;
    }

    private static void backtrack(List<List<BigDecimal>> result, List<BigDecimal> current, BigDecimal[] nums, BigDecimal target, int start) {
        if (target.compareTo(BigDecimal.ZERO) == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < nums.length && nums[i].compareTo(target) <= 0; i++) {
            if (i > start && nums[i].compareTo(nums[i - 1]) == 0) {
                // 跳过重复元素，避免重复匹配
                continue;
            }

            current.add(nums[i]);
            // 注意这里的 i + 1，限制下一轮搜索的起始位置
            backtrack(result, current, nums, target.subtract(nums[i]), i + 1);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        BigDecimal[] nums = {new BigDecimal("36.04"),new BigDecimal("36.04"),new BigDecimal("100"),new BigDecimal("45.05"),new BigDecimal("36.04"),new BigDecimal("464"),new BigDecimal("570.65"),new BigDecimal("630.71"),new BigDecimal("271.7"),new BigDecimal("89"),new BigDecimal("262.57"),new BigDecimal("273.36"),new BigDecimal("185"),new BigDecimal("459.53"),new BigDecimal("174.5"),new BigDecimal("1613"),new BigDecimal("300"),new BigDecimal("85"),new BigDecimal("927.5"),new BigDecimal("458"),new BigDecimal("63"),new BigDecimal("160.2"),new BigDecimal("2023"),new BigDecimal("858"),new BigDecimal("2000"),new BigDecimal("743")};
        BigDecimal target = new BigDecimal("2034.52");

        List<List<BigDecimal>> result = combinationSum1(nums, target);

        for (List<BigDecimal> combination : result) {
            System.out.println(combination);
        }
    }

    public static List<List<BigDecimal>> combinationSum1(BigDecimal[] nums, BigDecimal target) {
        List<List<BigDecimal>> result = new ArrayList<>();
        backtrack1(result, new ArrayList<>(), nums, target, 0);
        return result;
    }

    private static void backtrack1(List<List<BigDecimal>> result, List<BigDecimal> current, BigDecimal[] nums, BigDecimal remaining, int start) {
        if (remaining.compareTo(BigDecimal.ZERO) == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i].equals(nums[i - 1])) {
                // 跳过重复元素，避免重复匹配
                continue;
            }

            if (remaining.compareTo(nums[i]) >= 0) {
                // 优先选择相邻的数据组合
                if (!current.isEmpty() && nums[i].compareTo(current.get(current.size() - 1)) != 0) {
                    continue;
                }

                current.add(nums[i]);
                // 注意这里的 i，不限制下一轮搜索的起始位置
                backtrack(result, current, nums, remaining.subtract(nums[i]), i);
                current.remove(current.size() - 1);
            }
        }
    }
}
