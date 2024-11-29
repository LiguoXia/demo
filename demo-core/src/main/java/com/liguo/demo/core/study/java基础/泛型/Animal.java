package com.liguo.demo.core.study.java基础.泛型;

/**
 * <pre>
 * tag：
 * 泛型类可以有多个类型变量：
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/28 9:33
 * @since 0.0.1
 */
public class Animal<T, U> {
    private String name;
    private T mouth;
    private U eyes;

    public T getMouth() {
        return mouth;
    }
}
