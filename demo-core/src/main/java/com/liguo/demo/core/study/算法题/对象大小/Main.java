package com.liguo.demo.core.study.算法题.对象大小;


import com.liguo.demo.core.pojo.bo.BoDemo;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/15 12:52
 * @since 0.0.1
 */
public class Main {
    public static void main(String[] args) {
        BoDemo boDemo = new BoDemo();
        boDemo.setA("你好上世界");
        int size = estimateObjectSize(boDemo);
        System.out.println(size);
    }

    /**
     *
     * @param obj
     * @return 字节
     */
    public static int estimateObjectSize(Object obj) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.flush();
            return baos.size();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
