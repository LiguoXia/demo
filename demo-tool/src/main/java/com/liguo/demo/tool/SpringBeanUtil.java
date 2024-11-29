package com.liguo.demo.tool;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * SpringBeanUtil 是一个工具类，用于方便地从 Spring 容器中获取 Bean。
 * 它实现了 ApplicationContextAware 接口，以便在应用启动时自动注入 ApplicationContext。
 *
 * 可以改名为：SpringContextHolder  SpringContext的持有者
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/25 15:46
 * @since 0.0.1
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {
    // 使用 volatile 确保线程安全
    private static volatile ApplicationContext applicationContext;

    /**
     * 实现 ApplicationContextAware 接口的方法，自动注入 ApplicationContext。
     *
     * @param context Spring 应用上下文
     * @throws BeansException 如果获取上下文失败
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        // AnnotationConfigServletWebServerApplicationContext
        SpringBeanUtil.applicationContext = context;
    }

    /**
     * 检查 ApplicationContext 是否已被设置。
     *
     * @throws IllegalStateException 如果 ApplicationContext 未被注入
     */
    private static void assertContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("ApplicationContext 未被注入，请确保 SpringBeanUtil 已被正确初始化。");
        }
    }

    /**
     * 根据类型获取单个 Bean。
     *
     * @param clazz Bean 的类型
     * @param <T>   Bean 的类型参数
     * @return 类型匹配的 Bean 实例
     * @throws BeansException 如果找不到匹配的 Bean 或存在多个匹配的 Bean
     */
    public static <T> T getBean(Class<T> clazz) throws BeansException {
        assertContext();
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据 Bean 名称获取 Bean。
     *
     * @param name Bean 的名称
     * @return 名称匹配的 Bean 实例
     * @throws BeansException 如果找不到匹配的 Bean
     */
    public static Object getBean(String name) throws BeansException {
        assertContext();
        return applicationContext.getBean(name);
    }

    /**
     * 根据名称和类型获取 Bean。
     *
     * @param name  Bean 的名称
     * @param clazz Bean 的类型
     * @param <T>   Bean 的类型参数
     * @return 名称和类型匹配的 Bean 实例
     * @throws BeansException 如果找不到匹配的 Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) throws BeansException {
        assertContext();
        return applicationContext.getBean(name, clazz);
    }

    /**
     * 根据类型获取所有匹配的 Bean。
     *
     * @param clazz Bean 的类型
     * @param <T>   Bean 的类型参数
     * @return 类型匹配的 Bean 实例的 Map，其中 key 是 Bean 名称，value 是 Bean 实例
     * @throws BeansException 如果发生错误
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clazz) throws BeansException {
        assertContext();
        return applicationContext.getBeansOfType(clazz);
    }

    /**
     * 获取所有 Bean 名称。
     *
     * @return Bean 名称的数组
     * @throws BeansException 如果发生错误
     */
    public static String[] getBeanNames() throws BeansException {
        assertContext();
        return applicationContext.getBeanDefinitionNames();
    }

    /**
     * 获取所有特定类型的 Bean 名称。
     *
     * @param clazz Bean 的类型
     * @return 类型匹配的 Bean 名称的 Set
     * @throws BeansException 如果发生错误
     */
    public static String[] getBeanNamesForType(Class<?> clazz) throws BeansException {
        assertContext();
        return applicationContext.getBeanNamesForType(clazz);
    }

    /**
     * 检查容器中是否存在指定名称的 Bean。
     *
     * @param name Bean 的名称
     * @return 如果存在返回 true，否则返回 false
     */
    public static boolean containsBean(String name) {
        assertContext();
        return applicationContext.containsBean(name);
    }

    /**
     * 判断指定的 Bean 是否为单例。
     *
     * @param name Bean 的名称
     * @return 如果是单例返回 true，否则返回 false
     * @throws BeansException 如果找不到 Bean
     */
    public static boolean isSingleton(String name) throws BeansException {
        assertContext();
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取 Bean 的类型。
     *
     * @param name Bean 的名称
     * @return Bean 的 Class 类型
     * @throws BeansException 如果找不到 Bean
     */
    public static Class<?> getType(String name) throws BeansException {
        assertContext();
        return applicationContext.getType(name);
    }

    /**
     * 获取 Bean 的别名。
     *
     * @param name Bean 的名称
     * @return Bean 的别名数组
     * @throws BeansException 如果发生错误
     */
    public static String[] getAliases(String name) throws BeansException {
        assertContext();
        return applicationContext.getAliases(name);
    }

    public static String getProperty(String key) {
        Environment environment = applicationContext.getBean(Environment.class);
        String property =  applicationContext.getBean(Environment.class).getProperty(key);
        return property;
    }
}
