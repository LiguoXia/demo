package com.liguo.demo.core.controller;

import com.alibaba.fastjson.JSON;
import com.liguo.demo.core.service.DemoService;
import com.liguo.demo.core.service.impl.DemoServiceImpl;
import com.liguo.demo.core.service.pay.AlipayServiceImpl;
import com.liguo.demo.core.service.pay.PayService;
import com.liguo.demo.core.study.bean.initPost.MyServiceImpl;
import com.liguo.demo.core.study.beanLoadType.Import2.CartoonCatAndMouse;
import com.liguo.demo.core.study.beanLoadType.ImportBeanDefinitionRegistrar.MyImportBeanDefinitionRegistrar;
import com.liguo.demo.core.study.beanLoadType.ImportBeanDefinitionRegistrar.UserService;
import com.liguo.demo.core.study.beanLoadType.NotAutoLoadBeanLazy;
import com.liguo.demo.core.study.extensionPoint.springboot.factoryBean.NormalObjectFactoryBean;
import com.liguo.demo.core.study.extensionPoint.springboot.factoryBean.NormalObjectService;
import com.liguo.demo.core.study.静态方法注入bean.StaticFieldUseBean;
import com.liguo.demo.tool.SpringBeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

/**
 * SpringBeanUtil接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/29 16:42
 * @since 0.0.1
 */
@Api(tags = "beanUtil接口")
@Slf4j
@RestController
@RequestMapping("/beanUtil")
public class SpringBeanUtilController {
   /* @Autowired
    private NotAutoLoadBean notAutoLoadBean;*/

    @ApiOperation("根据名称获取bean")
    @PostMapping("/getBeanByName")
    public void getBeanByName(@RequestBody String beanName) {
        // 根据 Bean 名称获取
        // 没有指定 name 的情况,DemoServiceImpl 类的默认 Bean 名称为 demoServiceImpl（类名的首字母小写）
        //DemoService demoService = (DemoService) SpringBeanUtil.getBean(beanName);
        //demoService.findAll(); // 执行 DemoServiceImpl1
        Object obj = SpringBeanUtil.getBean(beanName);
        log.info("obj:{}", obj);
    }

    @ApiOperation("获取所有的bean")
    @PostMapping("/getAllBean")
    public String[] getAllBean() {
        String[] beans = SpringBeanUtil.getBeanNames();
        return beans;
    }

    @Autowired
    private NormalObjectService normalObjectService;

    @ApiOperation("根据bean类型获取")
    @PostMapping("/getBeanByType")
    public void getBeanByType() {
        DemoService demoService = SpringBeanUtil.getBean(DemoServiceImpl.class);
        // 只有一个实现类的,可以用接口
        DemoService demoService1 = SpringBeanUtil.getBean(DemoService.class);
        // 使用的时候才会去创建
        // NormalObjectService normalObjectService = SpringBeanUtil.getBean(NormalObjectService.class);
        // 这个不是bean
        // ApplicationContext applicationContext = SpringBeanUtil.getBean(ApplicationContext.class);
        normalObjectService.sayHello();

        // 获取的是getObject方法返回的对象
        NormalObjectService normalObjectService1 = (NormalObjectService) SpringBeanUtil.getBean("normalObjectFactoryBean");
        normalObjectService1.sayHello();

        // 获取的是getObject方法所属类的对象
        NormalObjectFactoryBean normalObjectFactoryBean = (NormalObjectFactoryBean) SpringBeanUtil.getBean("&normalObjectFactoryBean");
        return;
    }

    @ApiOperation("根据bean类型获取多个")
    @PostMapping("/getBeansOfType")
    public void getBeansOfType() {
        Map<String, PayService> map = SpringBeanUtil.getBeansOfType(PayService.class);
        Map<String, AlipayServiceImpl> map1 = SpringBeanUtil.getBeansOfType(AlipayServiceImpl.class);
        // Map<String, NotAutoLoadBean> map2 = SpringBeanUtil.getBeansOfType(NotAutoLoadBean.class);
        Map<String, MyServiceImpl> map3 = SpringBeanUtil.getBeansOfType(MyServiceImpl.class);

        for (Map.Entry<String, PayService> entry : map.entrySet()) {
            System.out.println("Bean Name: " + entry.getKey());
            entry.getValue().pay(888L);
        }
        return;
    }

    @ApiOperation("获取别名")
    @PostMapping("/getAliases")
    public void getAliases() {
        // @Bean的时候可以设置 @Bean(name = {"aliasTestService", "aliasTestServiceDemo"})
        String[] aliases = SpringBeanUtil.getAliases("aliasName1");
        // notAutoLoadBean.sayHello();
        log.info("别名:{}", JSON.toJSONString(aliases));
        return;
    }

    @ApiOperation("获取类型")
    @PostMapping("/getType")
    public void getType() {
        Class<?> aClass = SpringBeanUtil.getType("demoServiceImpl");
        log.info("aClass:{}", aClass);
        return;
    }

    @ApiOperation("获取属性")
    @PostMapping("/getProperty")
    public void getProperty() {
        String str = SpringBeanUtil.getProperty("spring.application.name");
        log.info("Property:{}", str);
        return;
    }

    @ApiOperation("静态方法中注入bean对象")
    @PostMapping("/staticUseBean")
    public void staticUseBean() throws MessagingException {
        StaticFieldUseBean.method1();
        StaticFieldUseBean.method2();
        return;
    }

    /**
     * 使用懒加载,项目启动后能从容器中获取notAutoLoadBeanLazy这个bean name,但是还没实例化初始化
     */
    @ApiOperation("懒加载")
    @GetMapping("/lazy-test")
    public String testLazyLoad() {
        // 手动从容器中获取 notAutoLoadBeanLazy，懒加载应在这里触发
        NotAutoLoadBeanLazy bean = SpringBeanUtil.getBean(NotAutoLoadBeanLazy.class);
        bean.sayHello();
        return "Lazy bean loaded";
    }

    /**
     * @see MyImportBeanDefinitionRegistrar#registerBeanDefinitions(org.springframework.core.type.AnnotationMetadata, org.springframework.beans.factory.support.BeanDefinitionRegistry)
     */
    @Autowired
    private UserService userService;

    @ApiOperation("替换bean实现")
    @PostMapping("/replaceBean")
    public void replaceBean() {
        userService.performAction();
    }

    @Autowired
    private CartoonCatAndMouse cartoonCatAndMouse;

    @ApiOperation("猫和老鼠")
    @PostMapping("/catAndMouse")
    public void catAndMouse() {
        cartoonCatAndMouse.play();
    }
}
