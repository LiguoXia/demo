package com.liguo.demo.core.study.java基础.泛型;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * <pre>
 * tag：
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/27 22:48
 * @since 0.0.1
 */

public class GenericExample<T, U> {
    // 当方法中使用了泛型（无论是参数还是返回值），并且这些泛型不属于类的泛型范围，就必须在返回类型前声明泛型类型。
    public <T> U echo(T input) {

        return (U) input;
    }

    public U echo(String input) {

        return (U) input;
    }

    public <K, V> Map<K, V> toMap(List<Pair<K, V>> pairs) {
        Map<K, V> map = new HashMap<>();
        for (Pair<K, V> pair : pairs) {
            map.put(pair.getKey(), pair.getValue());
        }
        return map;
    }

    public <T, U> void printPair(T first, U second) {
        System.out.println("First: " + first + ", Second: " + second);
    }

    public <T, R> R convert(T input, Function<T, R> converter) {
        return converter.apply(input);
    }

    public <T extends Number> double sum(List<T> numbers) {
        double total = 0;
        for (T number : numbers) {
            total += number.doubleValue();
        }
        return total;
    }

    public void processWithWildcard(List<? super String> list) {
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}