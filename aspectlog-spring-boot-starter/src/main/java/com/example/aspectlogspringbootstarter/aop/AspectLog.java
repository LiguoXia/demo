package com.example.aspectlogspringbootstarter.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志切面注解
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/25 22:06
 * @since 0.0.1
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AspectLog {
}
