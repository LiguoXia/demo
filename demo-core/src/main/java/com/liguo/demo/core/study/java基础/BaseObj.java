package com.liguo.demo.core.study.java基础;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 14:37
 * @since 0.0.1
 */
public class BaseObj {
    public String str;
    public int i;

    public BaseObj(String str, int i) {
        this.str = str;
        this.i = i;
    }

    @Override
    public String toString() {
        return "BaseObj{" +
                "str='" + str + '\'' +
                ", i=" + i +
                '}';
    }
}
