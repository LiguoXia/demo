package com.liguo.demo.core.study.bean.initPost;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/3/3 14:43
 * @since 0.0.1
 */
@Configuration
public class MyConfiguration {

    @Bean(initMethod = "init")
    public MyServiceImpl myService() {
        return new MyServiceImpl();
    }
}
