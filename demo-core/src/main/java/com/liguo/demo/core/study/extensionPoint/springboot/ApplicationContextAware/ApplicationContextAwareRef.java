package com.liguo.demo.core.study.extensionPoint.springboot.ApplicationContextAware;

import com.liguo.demo.core.service.pay.PayChooseContext;
import com.liguo.demo.tool.SpringBeanUtil;

/**
 * <pre>
 * BeanFactoryAware：
 * 作用： 允许 bean 实例获得对 BeanFactory 的引用。
 * 接口： org.springframework.beans.factory.BeanFactoryAware
 * 使用方式： 实现 BeanFactoryAware 接口，覆写 setBeanFactory 方法。
 *
 * BeanNameAware：
 * 作用： 允许 bean 实例获得自身在容器中的名字。
 * 接口： org.springframework.beans.factory.BeanNameAware
 * 使用方式： 实现 BeanNameAware 接口，覆写 setBeanName 方法。
 *
 * ApplicationContextAware：
 * 作用： 允许 bean 实例获得对 ApplicationContext 的引用。
 * 接口： org.springframework.context.ApplicationContextAware
 * 使用方式： 实现 ApplicationContextAware 接口，覆写 setApplicationContext 方法。
 *
 * EnvironmentAware：
 * 作用： 允许 bean 实例获得对 Environment 的引用。
 * 接口： org.springframework.context.EnvironmentAware
 * 使用方式： 实现 EnvironmentAware 接口，覆写 setEnvironment 方法。
 *
 * EmbeddedValueResolverAware：
 * 作用： 允许解析嵌入的值（例如，占位符）。
 * 接口： org.springframework.context.EmbeddedValueResolverAware
 * 使用方式： 实现 EmbeddedValueResolverAware 接口，覆写 setEmbeddedValueResolver 方法。
 *
 * ResourceLoaderAware：
 * 作用： 允许 bean 实例获得对 ResourceLoader 的引用。
 * 接口： org.springframework.context.ResourceLoaderAware
 * 使用方式： 实现 ResourceLoaderAware 接口，覆写 setResourceLoader 方法。
 *
 * MessageSourceAware：
 * 作用： 允许 bean 实例获得对 MessageSource 的引用。
 * 接口： org.springframework.context.MessageSourceAware
 * 使用方式： 实现 MessageSourceAware 接口，覆写 setMessageSource 方法。
 * </pre>
 *
 * @see PayChooseContext
 * @see SpringBeanUtil
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 1:11
 * @since 0.0.1
 */
public class ApplicationContextAwareRef {

}
