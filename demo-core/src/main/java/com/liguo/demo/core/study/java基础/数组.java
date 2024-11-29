package com.liguo.demo.core.study.java基础;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/17 15:52
 * @since 0.0.1
 */
public class 数组 {

    public static void main(String[] args) {

    }

    /**
     * Array获取数据的时间复杂度是O(1),但是要删除数据却是开销很大，因为这需要重排数组中的所有
     * 数据, (因为删除数据以后, 需要把后面所有的数据前移)
     * 缺点: 数组初始化必须指定初始化的长度, 否则报错
     */
    public static void array() {
        int[] a = new int[4];//推介使用int[] 这种方式初始化
        int c[] = {23, 43, 56, 78};//长度：4，索引范围：[0,3]
    }
}
