package com.liguo.demo.core.study.beanLoadType.ImportBeanDefinitionRegistrar;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 11:36
 * @since 0.0.1
 */
public class CustomUserServiceImpl implements UserService{
    @Override
    public void performAction() {
        System.out.println("UserService替换默认实现1");
    }
}
