package com.liguo.demo.core.study.beanLoadType.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/30 10:07
 * @since 0.0.1
 */
// @Configuration 注解： 表示这是一个配置类，用于配置 Spring Bean。
@Configuration
// @ConditionalOnProperty 注解： 这是一个条件化的注解，用于指定配置属性的条件。在这个例子中，它表示只有当名为 "uid.generator.local-enable" 的属性值为 "true" 时，才会创建并加载这个配置类中的 Bean。
@ConditionalOnProperty(prefix = "uid.generator", name = "local-enable", havingValue = "true")
// @ComponentScan({"com.sf.ct.uid"}) 注解： 这个注解指定了需要扫描的包路径，以查找标有 @Component 注解的类并注册为 Spring Bean。在这里，它会扫描 "com.sf.ct.uid" 包下的组件并注册为 Bean。
@ComponentScan({"com.sf.beic.*"})
public class ConditionConfig {
}
