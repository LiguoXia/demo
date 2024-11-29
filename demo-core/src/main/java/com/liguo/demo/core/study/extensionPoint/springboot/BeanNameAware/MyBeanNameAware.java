package com.liguo.demo.core.study.extensionPoint.springboot.BeanNameAware;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/28 20:09
 * @since 0.0.1
 */
@Component
public class MyBeanNameAware implements BeanNameAware {
    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
        System.out.println("Bean name is: " + name);
    }

    public void performTask() {
        System.out.println("Executing task in bean: " + beanName);
    }
}
