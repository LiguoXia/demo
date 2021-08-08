package com.liguo.demo.core.service.pay;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.*;
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

    /**
     * Set the ApplicationContext that this object runs in.
     * Normally this call will be used to initialize the object.
     * <p>Invoked after population of normal bean properties but before an init callback such
     * as {@link InitializingBean#afterPropertiesSet()}
     * or a custom init-method. Invoked after {@link ResourceLoaderAware#setResourceLoader},
     * {@link ApplicationEventPublisherAware#setApplicationEventPublisher} and
     * {@link MessageSourceAware}, if applicable.
     *
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws ApplicationContextException in case of context initialization errors
     * @throws BeansException              if thrown by application context methods
     * @see BeanInitializationException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, PayService> map = applicationContext.getBeansOfType(PayService.class);
        payServiceBeanMap = new HashMap<>();
        map.forEach((key, value) -> payServiceBeanMap.put(value.getCode(), value));
    }

    public static <T extends PayService> T getPayMode(PayTypeEnum code) {
        return (T) payServiceBeanMap.get(code);
    }
}
