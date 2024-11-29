package com.liguo.demo.core.study.extensionPoint.springboot.factoryBean;

import lombok.Data;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 0:14
 * @since 0.0.1
 */
@Data
public class NormalObjectService {
    private String a;
    private Integer b;

    public String sayHello() {
        System.out.println("Hello World");
        return "Hello World";
    }
}
