package com.liguo.demo.core.study.functionalInterface;

import org.springframework.beans.BeansException;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/19 17:05
 * @since 0.0.1
 */
@FunctionalInterface
public interface FunctionalInterface1<T> {
    T getObject(String str) throws BeansException;
}
