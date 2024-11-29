package com.liguo.demo.core.study.beanLoadType.ImportBeanDefinitionRegistrar;

import com.liguo.demo.core.pojo.entity.Demo;
import com.liguo.demo.core.service.DemoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 11:36
 * @since 0.0.1
 */
public class CustomUserServiceBImpl implements UserService{
    @Autowired
    private DemoService demoService;

    @Getter
    @Setter
    private String field = "111";
    @Override
    public void performAction() {
        System.out.println("UserService替换默认实现2, field:" + field);
        List<Demo> demoList = demoService.findAll();
    }
}
