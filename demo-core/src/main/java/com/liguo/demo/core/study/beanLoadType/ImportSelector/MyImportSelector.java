package com.liguo.demo.core.study.beanLoadType.ImportSelector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <pre>
 *     ImportSelector是Spring框架中的一个接口，用于选择要导入的Bean定义。通过实现该接口，我们可以编写自定义的逻辑来选择要导入的Bean定义。
 *     使用ImportSelector时，需要实现selectImports()方法，并返回要导入的Bean定义的完全限定名数组。
 *     在源码分析方面，ImportSelector接口的实现位于org.springframework.context.annotation包中。当Spring容器解析到ImportSelector时，会调用其selectImports()方法，并将返回的完全限定名数组注册到当前的应用上下文中。
 * @see EnableTransactionManagement
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 11:14
 * @since 0.0.1
 */
@Slf4j
public class MyImportSelector implements ImportSelector {

    /**
     * 实现selector，哪里注入的当前类，metadata指得就是哪里，可以动态控制加载的类
     *
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        boolean flag = importingClassMetadata.hasAnnotation(Configuration.class.getName());
        if (flag) {
            log.info("创建ImportClassA");
            // beanName:com.liguo.demo.core.study.beanLoadType.ImportSelector.ImportClassA"
            return new String[]{ImportClassA.class.getName()};
        } else {
            log.info("创建ImportClassB");
            return new String[]{ImportClassB.class.getName()};
        }
    }
}
