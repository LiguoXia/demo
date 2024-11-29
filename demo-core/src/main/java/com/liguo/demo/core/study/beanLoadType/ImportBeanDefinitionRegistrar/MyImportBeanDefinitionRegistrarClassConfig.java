package com.liguo.demo.core.study.beanLoadType.ImportBeanDefinitionRegistrar;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 11:22
 * @since 0.0.1
 */
@Configuration
@Import(MyImportBeanDefinitionRegistrar.class)
public class MyImportBeanDefinitionRegistrarClassConfig {
}
