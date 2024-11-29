package com.liguo.demo.core.study.list;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/10/25 22:58
 * @since 0.0.1
 */
@Slf4j
public class List2Map {
    public static Map<String, String> convertListToMap(List<MyObject> objectList) {
        Map<String, String> resultMap = new HashMap<>();

        for (MyObject obj : objectList) {
            String key = obj.getA() + obj.getB();
            String value = obj.getC();
            resultMap.put(key, value);
        }

        return resultMap;
    }

    public static Map<String, String> convertListToMap1(List<MyObject> objectList) {
        return objectList.stream()
                .collect(Collectors.toMap(obj -> obj.getA() + obj.getB(), MyObject::getC, (existing, replacement) -> replacement));
    }

    public static void main(String[] args) {
        for (int i = 1; i < 1000000; i++) {
            log.info("结果：{}", i/2);
        }
        // 测试用例
        MyObject obj1 = new MyObject("A1", "B1", "C1");
        MyObject obj2 = new MyObject("A2", "B2", "C2");
        MyObject obj3 = new MyObject("A3", "B3", "C3");
        MyObject obj4 = new MyObject("A3", "B3", "C4");

        List<MyObject> objectList = new ArrayList<>();
        objectList.add(obj1);
        objectList.add(obj2);
        objectList.add(obj3);
        objectList.add(obj4);

        Map<String, String> resultMap = convertListToMap1(objectList);

        // 打印结果
        for (Map.Entry<String, String> entry : resultMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
    }
}

class MyObject {
    private String a;
    private String b;
    private String c;

    public MyObject(String a, String b, String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }
}