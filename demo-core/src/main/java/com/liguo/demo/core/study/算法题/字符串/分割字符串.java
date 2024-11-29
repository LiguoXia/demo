package com.liguo.demo.core.study.算法题.字符串;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割字符串
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/7 21:39
 * @since 0.0.1
 */
public class 分割字符串 {
    public static String[] customSplit(String input, char delimiter) {
        List<String> result = new ArrayList<>();

        int start = 0; // 记录每个分割段的起始位置

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == delimiter) {
                // 当遇到分隔符时，截取起始位置到当前位置的子串，并加入结果列表
                result.add(input.substring(start, i));
                start = i + 1; // 更新起始位置
            }
        }

        // 处理最后一个分割段
        result.add(input.substring(start));

        // 将结果列表转为数组
        String[] resultArray = new String[result.size()];
        return result.toArray(resultArray);
    }

    public static void main(String[] args) {
        String input = "apple,orange,banana,grape";
        char delimiter = ',';

        String[] result = customSplit(input, delimiter);

        for (String item : result) {
            System.out.println(item);
        }
    }
}
