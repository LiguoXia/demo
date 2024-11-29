package com.liguo.demo.core.study.java基础;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Array（数组）是基于索引(index)的数据结构，它使用索引在数组中搜索和读取数据是很快的
 *
 * ArrayList: 可以看作是能够自动增长容量的数组
 *
 * LinkList: 是一个双链表,在添加和删除元素时具有比ArrayList更好的性能.但在get与set方面弱于ArrayList.当然,这些对比都是指数据量很大或者操作很频繁。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 14:25
 * @since 0.0.1
 */
@Slf4j
public class ArrayList与LinkedList {

    public static void main(String[] args) {
        arr1();
    }

    /**
     * ArrayList的**快速失败机制（Fail-Fast机制）**指的是在多线程环境下，如果一个线程修改了ArrayList的结构（增加、删除或修改元素），
     * 那么其他线程在访问ArrayList时，如果发现modCount属性（记录ArrayList结构修改次数的属性）与自己持有的modCount属性不一致，
     * 就会抛出ConcurrentModificationException异常，从而防止多个线程同时修改ArrayList的结构，导致数据不一致的情况发生。
     */
    public static void arr() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("234");
        Object[] strings = list.toArray();
        for (String str: list) {
            log.info("str:{}", str);
            list.add("12"); // ConcurrentModificationException
            log.info("str:{}", str);
        }
    }

    public static void arr1() {
        List<BaseObj> list = new ArrayList<>();
        list.add(new BaseObj("123", 1));
        list.add(new BaseObj("234", 2));
        for (BaseObj obj : list) {
            log.info("obj:{}", obj.toString());
            obj = new BaseObj("456", 3);
            log.info("obj:{}", obj.toString());
        }
    }
}
