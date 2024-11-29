package com.liguo.demo.core.study.集合;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Hash原理
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/11/30 16:44
 * @since 0.0.1
 */
@Slf4j
public class HashMapStudy {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap<>();
        for (int i = 0; i < 13; i++) {
            hashMap.put(i, "12");
            print(hashMap);

        }


        // 给定初始容量,避免频繁扩容
        HashMap hashMap1 = new HashMap<>(100);
        log.info("e的hashcode:{}, 16取模:{}", "世".hashCode(), "e".hashCode() % 16);

        put();
    }

    public static void put() {
        String[] a = {"a", "b", "d", "r", "t", "e", "g", "a", "i"};
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            map.put(a[i], i + 1);
            print(map);
        }
        map.put(null, 10);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            log.info("key:{}", key);
        }
    }

    private static void print(HashMap hashMap) {
        int threshold = 0;
        try {
            Field thresholdField = HashMap.class.getDeclaredField("threshold");
            thresholdField.setAccessible(true);
            threshold = (int) thresholdField.get(hashMap);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            // 处理异常
        }

        float loadFactor = 0;

        try {
            Field loadFactorField = HashMap.class.getDeclaredField("loadFactor");
            loadFactorField.setAccessible(true);
            loadFactor = (float) loadFactorField.get(hashMap);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            // 处理异常
        }

        int capacity = 0;


        try {
            Field tableField = HashMap.class.getDeclaredField("table");
            tableField.setAccessible(true);
            Object[] table = (Object[]) tableField.get(hashMap);
            capacity = table == null ? 0 : table.length;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            // 处理异常
        }

        int modCount = 0;
        try {
            Field mdlCountField = HashMap.class.getDeclaredField("modCount");
            mdlCountField.setAccessible(true);
            modCount = (int) mdlCountField.get(hashMap);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            // 处理异常
        }
        log.info("实际键值对数量:{}, 装载因子:{}, HashMap实际容量:{}, 乘积:{}, 阈值:{}, 是否大于等于:{}, count:{}",
                hashMap.size(), loadFactor, capacity, loadFactor * capacity, threshold, hashMap.size() >= threshold, modCount);
    }
}
