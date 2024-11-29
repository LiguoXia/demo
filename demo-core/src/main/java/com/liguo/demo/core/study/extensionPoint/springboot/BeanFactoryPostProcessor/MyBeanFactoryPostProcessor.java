package com.liguo.demo.core.study.extensionPoint.springboot.BeanFactoryPostProcessor;

import com.liguo.demo.core.study.beanLoadType.BeanDefinitionRegistryPostProcessor.CustomBeanDefinitionRegistryPostProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 应用场景
 * BeanFactoryPostProcessor可以对Bean配置元数据进行操作。也就是说，Spring容器允许BeanFactoryPostProcessor读取指定Bean的配置元数据，并可以在Bean被实例化之前修改它。
 * 这里说的配置元数据其实就是我们之前讲过的BeanDefinition。
 *
 *
 * Bean 的作用域有哪些
 * Singleton	默认作用域，容器中仅有一个实例	     容器生命周期内	       无状态服务类、工具类
 * Prototype	每次请求都会创建新实例	         调用方自行管理	       有状态的对象、每次需要新实例的对象
 * Request	    每个 HTTP 请求拥有一个实例	     HTTP 请求生命周期	   Web 应用中的请求相关对象
 * Session	    每个 HTTP 会话拥有一个实例	     HTTP 会话生命周期	   用户会话数据、购物车等
 * Application	整个 Web 应用共享一个实例	Web      应用生命周期	       全局配置或服务
 * WebSocket	每个 WebSocket 会话拥有一个实例	 WebSocket会话生命周期	WebSocket 连接管理
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/28 19:12
 * @since 0.0.1
 */
@Slf4j
@Component
// @Scope(value = "singleton")
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * @see CustomBeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry(org.springframework.beans.factory.support.BeanDefinitionRegistry)
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.info("BeanFactoryPostProcessor只会调用一次");
        // 获取指定 Bean 的定义
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("userService11");

        // 修改 Bean 的作用域为原型
        // beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);

        // 修改某个属性的默认值
        beanDefinition.getPropertyValues().add("field", "newValue222");
    }


}
