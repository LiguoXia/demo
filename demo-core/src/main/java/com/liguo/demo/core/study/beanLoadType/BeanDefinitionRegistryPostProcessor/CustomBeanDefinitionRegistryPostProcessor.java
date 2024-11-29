package com.liguo.demo.core.study.beanLoadType.BeanDefinitionRegistryPostProcessor;

import com.liguo.demo.core.study.beanLoadType.ImportBeanDefinitionRegistrar.CustomUserServiceBImpl;
import com.liguo.demo.core.study.beanLoadType.ImportBeanDefinitionRegistrar.MyImportBeanDefinitionRegistrar;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * 导入实现了BeanDefinitionRegistryPostProcessor接口的类，通过BeanDefinition的注册器注册实名bean 实现对容器中bean的最终裁定
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 11:47
 * @since 0.0.1
 */
@Component
public class CustomBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        /**
         * 二次覆盖已有的 UserService
         * 一次覆盖:
         * @see MyImportBeanDefinitionRegistrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata, org.springframework.beans.factory.support.BeanDefinitionRegistry)
         */
        if (registry.containsBeanDefinition("defaultUserServiceImpl")) {
            registry.removeBeanDefinition("defaultUserServiceImpl");
        }

        // 注册新的 UserService
        /**
         * @see CustomUserServiceBImpl#performAction()
         */
        RootBeanDefinition beanDefinition = new RootBeanDefinition(CustomUserServiceBImpl.class);
        registry.registerBeanDefinition("userService11", beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
