package com.liguo.demo.core.study.静态方法注入bean;

import com.alibaba.fastjson.JSON;
import com.liguo.demo.core.pojo.entity.Demo;
import com.liguo.demo.core.service.DemoService;
import com.liguo.demo.core.service.IMailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import java.util.List;

/**
 * 静态方法中注入bean对象
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/26 14:34
 * @since 0.0.1
 */
@Slf4j
@Component
public class StaticFieldUseBean {

    private static DemoService demoService;

    private static IMailService mailService;

    @Autowired
    private IMailService autowiredMailService;

    // 方式1 通过构造方法注入
    @Autowired
    public StaticFieldUseBean(DemoService demoService) {
        this.demoService = demoService;
    }

    // 方式2
    @PostConstruct
    public void init() {
        mailService = autowiredMailService;
    }

    public static void method1() {
        List<Demo> demoList = demoService.findAll();
        log.info("静态方法:{}", JSON.toJSONString(demoList));
    }

    public static void method2() throws MessagingException {
        mailService.sendAttachmentsMail("", "", "", "");
    }
}
