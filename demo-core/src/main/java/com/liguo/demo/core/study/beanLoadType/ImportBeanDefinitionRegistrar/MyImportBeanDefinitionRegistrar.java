package com.liguo.demo.core.study.beanLoadType.ImportBeanDefinitionRegistrar;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 可以直接访问 BeanDefinitionRegistry，通过该接口你可以直接操作 Bean 的定义和注册。
 * 通过 ImportBeanDefinitionRegistrar，我们可以在运行时检查并覆盖现有的 Bean 定义，而无需修改源代码。这种方式在以下场景中非常有用：
 * <p>
 * 替换第三方库中的默认实现。
 * 在不修改现有代码的情况下进行定制化配置。
 * 针对不同环境、模块或配置条件，动态地注册或覆盖 Bean。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 11:22
 * @since 0.0.1
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    /**
     * 注册bean
     *
     * @param importingClassMetadata 提供当前类的元数据，包含被 @Import 注解标注的类的信息。
     * @param registry               用于注册 BeanDefinition 的接口，你可以通过它将新的 Bean 定义注册到容器中。
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition beanDefinition = BeanDefinitionBuilder
                .rootBeanDefinition(ImportBeanDefinitionRegistrarClassA.class)
                .getBeanDefinition();
        registry.registerBeanDefinition("importBeanDefinitionRegistrarClassA111", beanDefinition);


        // 使用场景1 替换默认实现
        // 检查容器中是否已有名为 "userService" 的 Bean
        if (registry.containsBeanDefinition("defaultUserServiceImpl")) {
            // 移除已有的 Bean 定义
            registry.removeBeanDefinition("defaultUserServiceImpl");
        }

        // 注册新的 CustomUserService 作为 "userService"
        RootBeanDefinition beanDefinition1 = new RootBeanDefinition(CustomUserServiceImpl.class);
        registry.registerBeanDefinition("defaultUserServiceImpl", beanDefinition1);
    }

}
