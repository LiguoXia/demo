package com.liguo.demo.core.study.beanLoadType.Import2;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 1、除了可以用@Import导入配置类
 * 2、也可以用注解的方式注入
 * 3、org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
 * 包全路径名.配置类类名
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 15:03
 * @since 0.0.1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({CartoonCatAndMouse.class})
public @interface EnableCartoon {
}
