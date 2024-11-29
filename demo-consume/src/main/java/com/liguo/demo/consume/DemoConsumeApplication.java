package com.liguo.demo.consume;

import com.liguo.demo.consume.config.CartoonCatAndMouse;
import com.liguo.demo.tool.annotation.EnableBaseFeignInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Properties;

/*(exclude = {DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        JdbcTemplateAutoConfiguration.class})*/
//@SpringBootApplication(scanBasePackages = {"com.liguo.*","com.liguo.demo*"})
//@SpringBootApplication(scanBasePackages = {"com.liguo.demo","cn.itcast"})
@SpringBootApplication
@EnableFeignClients(basePackages = "com.liguo.demo.consume.*")
@RestController
@Slf4j
// , SpringMvcConfig.class
@Import({CartoonCatAndMouse.class})
@MapperScan(basePackages = "com.liguo.demo.consume.dao")
// 开启feign拦截器传递数据给下游服务
@EnableBaseFeignInterceptor
public class DemoConsumeApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication app = new SpringApplication(DemoConsumeApplication.class);
        //SpringApplication.run(DemoConsumeApplication.class, args);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext applicationContext = app.run(args);
        log.info("^-^####################################################^-^");
        log.info("^-^##### Consume service was started,{} senconds.#####^-^", System.currentTimeMillis() - startTime);
        log.info("^-^####################################################^-^");


        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames);

        log.info("========================列出加载的bean开始======================================");
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
        log.info("共加载:{}个bean", beanNames.length);
        log.info("========================列出加载的bean结束======================================");

        log.info("==========================测试自动装配开始====================================");
        log.info("自动装配");
        CartoonCatAndMouse bean = applicationContext.getBean(CartoonCatAndMouse.class);
        bean.play();
        log.info("==========================测试自动装配结束====================================");


        log.info("==========================获取系统相关配置开始====================================");
        Properties properties = System.getProperties();
        properties.list(System.out);
        log.info("==========================获取系统相关配置结束====================================");
    }

    @GetMapping("/status")
    public String status() {
        return "ok";
    }

}
