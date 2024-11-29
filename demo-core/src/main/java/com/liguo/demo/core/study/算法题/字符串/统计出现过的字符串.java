package com.liguo.demo.core.study.算法题.字符串;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你两个字符串数组 words1 和 words2 ，请你返回在两个字符串数组中 都恰好出现一次 的字符串的数目。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：words1 = ["leetcode","is","amazing","as","is"], words2 = ["amazing","leetcode","is"]
 * 输出：2
 * 解释：
 * - "leetcode" 在两个数组中都恰好出现一次，计入答案。
 * - "amazing" 在两个数组中都恰好出现一次，计入答案。
 * - "is" 在两个数组中都出现过，但在 words1 中出现了 2 次，不计入答案。
 * - "as" 在 words1 中出现了一次，但是在 words2 中没有出现过，不计入答案。
 * 所以，有 2 个字符串在两个数组中都恰好出现了一次。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/12 22:43
 * @since 0.0.1
 */
@Slf4j
public class 统计出现过的字符串 {

    public static void main(String[] args) {
        String[] words1 = {"b", "bb", "bbb"};
        String[] words2 = {"a", "bb", "aaa"};
        int count = countWords(words1, words2);
        log.info("两个数组中都出现过且仅出现过一次的字符串个数是:{}", count);
    }

    public static int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < words1.length; i++) {
            map.put(words1[i], map.getOrDefault(words1[i], 0) + 1);
        }
        for (String key : words2) {
            map2.put(key, map2.getOrDefault(key, 0) + 1);
        }
        int count = 0;
        for (String str : map.keySet()) {
            if (map.get(str) == 1 && map2.getOrDefault(str, 0) == 1) {
                count++;
            }
        }
        return count;
    }
}
