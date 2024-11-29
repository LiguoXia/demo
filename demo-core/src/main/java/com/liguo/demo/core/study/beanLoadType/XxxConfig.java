package com.liguo.demo.core.study.beanLoadType;

import com.liguo.demo.core.study.SPI.MyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.*;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.sql.DataSource;

/**
 * <pre>
 * Spring Boot 提供的条件注解
 *
 * 1 @ConditionalOnBean：当容器里有指定 Bean 的条件下
 * 2 @ConditionalOnMissingBean：当容器里没有指定 Bean 的情况下
 * 3 @ConditionalOnSingleCandidate：当指定 Bean 在容器中只有一个，或者虽然有多个但是指定首选 Bean
 * 4 @ConditionalOnClass：当类路径下有指定类的条件下@ConditionalOnMissingClass：当类路径下没有指定类的条件下
 * 5 @ConditionalOnProperty：指定的属性是否有指定的值@ConditionalOnResource：类路径是否有指定的值
 * 6 @ConditionalOnExpression：基于 SpEL 表达式作为判断条件
 * 7 @ConditionalOnJava：基于 Java 版本作为判断条件@ConditionalOnJndi：在 JNDI 存在的条件下差在指定的位置@ConditionalOnNotWebApplication：当前项目不是 Web 项目的条件下
 * 8 @ConditionalOnWebApplication：当前项目是 Web 项 目的条件下
 *
 * 9 @AutoConfigureBefore、@AutoConfigureAfter
 *      说明：
 *      1、指定自动装配配置之间的加载顺序。
 *      2、被注解修饰的类要在spring.factories中的org.springframework.boot.autoconfigure.EnableAutoConfiguration指定，不能用@Configuration修饰，否则会立刻被spring扫描到，不能实现指定顺序
 *      3、指定配置类加载的顺序，而不是类中bean的加载顺序
 *      4、@AutoConfigureBefore(A.class)
 *         public class C {
 *         }
 *         C在A之前加载
 *      5、若A.class不满足加载条件，不影响C的加载，它只影响加载顺序
 *
 *  配置类加载说明：
 *  1、配置类的加载是 Spring 容器中的概念
 *  2、配置类通常通过 @Configuration 注解标识，Spring 会识别它，并将其作为一部分来解析 Bean 定义和处理相关逻辑。
 *  3、配置类本身会被 Spring 创建为一个 Bean
 *  4、配置类加载主要是为了注册 Bean 定义，而不是立即实例化所有 Bean。
 *
 * </pre>
 */
@Slf4j
@Configuration
// 该类中的所有 Bean 和配置都受到该条件的控制
@ConditionalOnProperty(prefix = "demo.bean", name = "enable", havingValue = "true")
public class XxxConfig {

    // 项目启动时加载
    // 它仅控制该方法生成的单个 Bean 是否加载
    // 通过 @Bean 注解注册的 Spring Bean，如果没有显式指定 name 或 value 属性，那么默认的 Bean 名称是方法名称
    // @ConditionalOnProperty(prefix = "demo.bean", name = "enable", havingValue = "true", matchIfMissing = true)
    @Bean(name = {"aliasName1", "aliasName2", "aliasName3"})
    // @Bean
    public NotAutoLoadBean notAutoLoadBeanName() {
        log.info("通过@Bean注入bean");
        NotAutoLoadBean bean = new NotAutoLoadBean();
        return bean;
    }

    /**
     * 作用：这是 Spring 4 引入的注解，用于根据某些条件来控制 Bean 的创建。可以通过实现 Condition 接口来自定义条件。
     * 使用场景：需要基于复杂逻辑控制 Bean 的加载时，可以实现自定义条件。
     */
    @Bean
    @Conditional(MyCustomCondition.class)
    public NotAutoLoadBean defaultMyService() {
        log.info("通过@Bean注入bean,自定义用户条件");
        return new NotAutoLoadBean();
    }

    /**
     * 作用：只有当 Spring 容器中不存在指定类型的 Bean 时，才会创建当前的 Bean。
     * 使用场景：可以用于确保在特定 Bean 未定义的情况下提供默认实现。
     */
    @Bean
    @ConditionalOnMissingBean(MyService.class)
    public NotAutoLoadBean defaultMyService1() {
        return new NotAutoLoadBean();
    }

    /**
     * 作用：只有当容器中存在指定类型的 Bean 时，才会创建当前的 Bean。
     * 使用场景：可以用于只有在某些其他 Bean 已经加载时才启用某个功能。
     */
    @Bean
    @ConditionalOnBean(DataSource.class)
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    /**
     * 作用：当类路径上存在指定的类时，才会创建当前的 Bean。
     * 使用场景：通常用于仅在某个依赖存在时启用某些功能，例如自动配置某个外部库的功能。
     */
    @Bean
    @ConditionalOnClass(name = "com.example.SomeExternalLibrary")
    public NotAutoLoadBean someService1() {
        return new NotAutoLoadBean();
    }

    /**
     * 作用：当类路径上不存在指定的类时，才会创建当前的 Bean。
     * 使用场景：用于在某个依赖缺失时提供替代方案。
     */
    @Bean
    @ConditionalOnMissingClass("com.example.SomeExternalLibrary")
    public NotAutoLoadBean someService2() {
        return new NotAutoLoadBean();
    }

    /**
     * 作用：只有当类路径下存在指定的资源（文件、配置等）时，才会创建当前的 Bean。
     * 使用场景：用于检查类路径或文件系统中是否存在某个文件或资源。
     */
    @Bean
    @ConditionalOnResource(resources = "classpath:/config/some-config.xml")
    public NotAutoLoadBean configService() {
        return new NotAutoLoadBean();
    }

    /**
     * 作用：只有在当前应用程序是 Web 应用时，才会创建当前的 Bean。
     * 使用场景：用于区分应用程序的 Web 和非 Web 环境。
     */
    @Bean
    @ConditionalOnWebApplication
    // @ConditionalOnNotWebApplication
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            // Web MVC configuration
        };
    }

    /**
     * 作用：根据当前激活的 Spring Profile 来决定是否加载某个 Bean。
     * 使用场景：用于根据不同的环境（如 dev、test、prod）加载不同的配置或 Bean。
     */
    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        return (DataSource) new Object();
    }

    /**
     * 作用：当存在多个同类型的 Bean 时，标记某个 Bean 为首选注入对象。
     * 使用场景：用于多个 Bean 类型相同时，指定默认使用哪个 Bean。
     */
    @Bean
    @Primary
    public NotAutoLoadBean primaryService() {
        return new NotAutoLoadBean();
    }

    @Bean
    public NotAutoLoadBean secondaryService() {
        return new NotAutoLoadBean();
    }

    /**
     * 作用：指定某个 Bean 以懒加载（Lazy Loading）方式注入，只有在真正使用时才会被初始化。
     * 使用场景：用于推迟 Bean 的初始化，尤其是在 Bean 初始化过程比较耗时时。
     * 说明: 使用懒加载,项目启动后能从容器中获取notAutoLoadBeanLazy这个bean name,但是还没实例化初始化
     */
    @Bean
    @Lazy
    public NotAutoLoadBeanLazy notAutoLoadBeanLazy() {
        log.info("notAutoLoadBeanLazy 懒加载");
        return new NotAutoLoadBeanLazy();
    }
}
