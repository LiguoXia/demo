package com.liguo.demo.core.study.extensionPoint.springboot.factoryBean;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * FactoryBean 是一个工厂模式接口，用于创建复杂的 Bean 实例，它可以根据需求定制 Bean 的创建过程，尤其是在涉及代理对象或复杂初始化逻辑的情况下使用。
 * // 获取的是getObject方法返回的对象
 * System.out.println(context.getBean("normalObjectFactoryBean"));
 * // 获取的是getObject方法所属类的对象
 * System.out.println(context.getBean("&normalObjectFactoryBean"));
 *
 * BeanFactory 是 Spring 框架的核心接口，用于管理和提供 Bean 实例，而 FactoryBean 是一个特殊的 Bean，用于创建和管理其他 Bean 的实例。FactoryBean 在 Bean 的创建过程中提供更多的自定义能力，允许进行额外的逻辑处理。
 * https://juejin.cn/post/6844903967600836621
 *
 * @see BeanFactory
 * @see FactoryBean
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 0:16
 * @since 0.0.1
 */
@Component
public class NormalObjectFactoryBean implements FactoryBean<NormalObjectService> {

    // 使用的时候才会去创建
    @Override
    public NormalObjectService getObject() throws Exception {
        System.out.println("通过FactoryBean创建了NormalObjectService bean");
        NormalObjectService normalObjectService = new NormalObjectService();
        normalObjectService.setA("a");
        normalObjectService.setB(2);
        return normalObjectService;
    }

    @Override
    public Class<?> getObjectType() {
        return NormalObjectService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
