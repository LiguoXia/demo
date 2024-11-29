package com.liguo.demo.core.study.bean.initPost;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 用于在 Spring 容器初始化 bean 的前后对 bean 进行一些自定义的处理操作。通过实现 BeanPostProcessor，你可以在 Spring 容器中所有 bean 的初始化过程中插入一些自定义的逻辑。
 * <pre>
 * 典型用途包括:
 * 1、在 bean 初始化前后执行自定义逻辑：可以在所有 bean 初始化前后进行一些通用操作，例如修改属性、代理 bean、执行日志等。
 * 2、动态代理：可以通过拦截某些 bean 并为它们创建代理，从而增加横切逻辑（类似 AOP）。
 * 3、执行全局的行为：例如，对某些类型的 bean 进行特殊处理，或者在 bean 完成初始化后注册一些资源或进行额外的设置。
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/3/3 14:44
 * @since 0.0.1
 */
@Slf4j
@Component
public class MyPostProcessorBean implements BeanPostProcessor {

    /**
     * 后置处理在 初始化之前 Instantiation为实例化 Initialization初始化
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("Bean:{}初始化之前执行操作", beanName);
        return bean;
    }

    /**
     * 后置处理在 初始化之后
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        log.info("Bean:{}初始化之后执行操作", beanName);
        return bean;
    }
}
