package com.example.aspectlogspringbootstarter.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志切面注解
 * <pre>
 * @author ：zoe
 * Target 的注解类型    适用场景
 *    TYPE              类(包括Enum)接口
 *    PACKAGE           包
 *    METHOD            方法
 *    FIELD             成员域(包括Enum常量)
 *    CONSTRUCTOR       构造器
 *    PARAMETER         方法或构造器参数
 *    LOCAL_VARIABLE    本地变量
 *    ANNOTATION_TYPE   注解类型声明
 *    java 8 新加
 *    TYPE_PARAMETER    类型参数声明
 *    TYPE_USE          类型的使用
 * Retention的保留策略
 *    保留规则           描述
 *    SOURCE             注释将被编译器丢弃,不包括在类文件中
 *    CLASS              注释由编译器记录在类文件中,但是不需要在运行时被虚拟机(VM)保留。默认策略
 *    RUNTIME            注释由编译器记录在类文件中，并在运行时由VM保存，因此可以反射可读取它们
 * </pre>
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
