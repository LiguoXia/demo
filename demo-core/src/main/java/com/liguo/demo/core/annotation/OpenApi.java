package com.liguo.demo.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解,用来标识请求类或者方法是否使用AOP加密解密
 * <P>用于开放接口使用</P>
 * <p>使用方法:</p>
 * <p>1.在类上使用注解;类中所有方法都执行切面加签验签、加解密逻辑</p>
 * <p>2.在方法上使用注解;仅当前方法执行切面加签验签、加解密逻辑</p>
 *
 * @author xialiguo0212@gmail.com
 * @version 1.1
 * @date 2020/9/17
 * @see com.liguo.demo.core.aop.SecretAOPController
 * @since 1.1
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenApi {

}