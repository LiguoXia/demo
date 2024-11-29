package com.liguo.demo.core.study.beanLoadType.ImportBeanDefinitionRegistrar;

import org.springframework.stereotype.Service;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 11:36
 * @since 0.0.1
 */
@Service
public class DefaultUserServiceImpl implements UserService{
    @Override
    public void performAction() {
        System.out.println("UserService默认实现");
    }
}
