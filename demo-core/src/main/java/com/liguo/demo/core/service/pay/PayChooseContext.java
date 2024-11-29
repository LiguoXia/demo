package com.liguo.demo.core.service.pay;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付选择
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/29 22:33
 * @since 0.0.1
 */
@Component
public class PayChooseContext implements ApplicationContextAware {
    private static Map<PayTypeEnum, PayService> payServiceBeanMap;

    // ApplicationContext --> AnnotationConfigServletWebServerApplicationContext
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, PayService> map = applicationContext.getBeansOfType(PayService.class);
        payServiceBeanMap = new HashMap<>();
        map.forEach((key, value) -> payServiceBeanMap.put(value.getCode(), value));
        System.out.println(getClass().getSimpleName() + "所属IoC容器的地址值："
                + ("@" + Integer.toHexString(applicationContext.hashCode())));
    }

    public static <T extends PayService> T getPayMode(PayTypeEnum code) {
        return (T) payServiceBeanMap.get(code);
    }
}
