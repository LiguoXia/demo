package com.liguo.demo.consume.config;

import com.liguo.demo.consume.pojo.bos.Cat;
import com.liguo.demo.consume.pojo.bos.Mouse;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/9 21:12
 * @since 0.0.1
 */
//@Component
// 不自动装配, 交给使用方CartoonCatAndMouse 使用@EnableConfigurationProperties(CartoonProperties.class)加载bean
// 交给使用方CartoonCatAndMouse也不自动装配,使用@Import(CartoonCatAndMouse.class) 装配, 这样可以解耦,不用强制加载bean
@ConfigurationProperties(prefix = "cartoon")
@Data
public class CartoonProperties {
    private Cat cat;

    private Mouse mouse;
}
