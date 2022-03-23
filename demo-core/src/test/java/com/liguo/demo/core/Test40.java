package com.liguo.demo.core;

import cn.hutool.core.collection.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/12/25 19:45
 * @since 0.0.1
 */
public class Test40 {
    public static void main(String[] args) {
        Integer[] candidates = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        System.out.println(combinationSum2(candidates, 12));
    }
    //static List<List<Integer>> lists = new ArrayList<>();

    public static List<List<Integer>> combinationSum2(Integer[] candidates, int target) {
        List<List<Integer>> lists = new ArrayList<>();
        // Arrays.sort(candidates);
        Arrays.sort(candidates, Collections.reverseOrder());
        //对数组进行排序   这个很关键
        if (candidates == null || candidates.length == 0 || target < 0) {
            return lists;
        }
        List<Integer> list = new ArrayList<>();
        dfs(lists, list, candidates, target, 0);
        return lists;
    }

    private static void dfs(List<List<Integer>> lists, List<Integer> list, Integer[] candidates, int target, int start) {
        //递归的终止条件
        if (target < 0 || CollectionUtil.isNotEmpty(lists)) {
            return;
        }
        if (target == 0) {
            lists.add(new ArrayList<>(list));
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                //因为这个数和上个数相同，所以从这个数开始的所有情况，在上个数里面都考虑过了，需要跳过
                /*if (i != start && candidates[i] == candidates[i - 1]) {
                    continue;
                }*/
                list.add(candidates[i]);
                dfs(lists, list, candidates, target - candidates[i], i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
}
