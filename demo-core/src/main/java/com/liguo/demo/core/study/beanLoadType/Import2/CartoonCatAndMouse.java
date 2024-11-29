package com.liguo.demo.core.study.beanLoadType.Import2;

import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.StringUtils;

/**
 * 参阅https://blog.csdn.net/qq_41907991/article/details/103589884
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/9 21:15
 * @since 0.0.1
 */
//@Component
@Data
@EnableConfigurationProperties(CartoonProperties.class)
public class CartoonCatAndMouse {

    private Cat cat;

    private Mouse mouse;

    private CartoonProperties cartoonProperties;

    /**
     * Spring Boot首选无参构造方法，如果不存在，则尝试使用带有参数的构造方法进行自动装配。
     * 并尝试通过自动装配的方式提供构造方法所需的参数
     *
     * 总结：Spring推断构造逻辑如下
     *
     * 如果一个类只有一个构造方法，那没的选择，只能用这个构造方法
     * 如果一个类有多个构造方法，首先会判断是否有@Autowired指定构造方法，再看是否有无参构造方法。如果都没有就会报错 No default constructor found
     * [参阅](https://blog.csdn.net/tjd5214/article/details/120066029)
     *
     * @param cartoonProperties
     */
    public CartoonCatAndMouse(CartoonProperties cartoonProperties) {
        System.out.println("222222222222222222");
        this.cartoonProperties = cartoonProperties;
        cat = new Cat();
        cat.setName(cartoonProperties.getCat() != null && StringUtils.hasText(cartoonProperties.getCat().getName()) ? cartoonProperties.getCat().getName() : "tom");
        cat.setAge(cartoonProperties.getCat() != null && cartoonProperties.getCat().getAge() != null ? cartoonProperties.getCat().getAge() : 3);
        mouse = new Mouse();
        mouse.setName(cartoonProperties.getMouse() != null && StringUtils.hasText(cartoonProperties.getMouse().getName()) ? cartoonProperties.getMouse().getName() : "jerry");
        mouse.setAge(cartoonProperties.getMouse() != null && cartoonProperties.getMouse().getAge() != null ? cartoonProperties.getMouse().getAge() : 4);
    }

    public void play() {
        System.out.println(cat.getAge() + "岁的" + cat.getName() + "和" + mouse.getAge() + "岁的" + mouse.getName() + "打起来了");
    }
}
