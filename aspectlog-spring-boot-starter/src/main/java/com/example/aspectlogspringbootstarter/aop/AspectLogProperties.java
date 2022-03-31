package com.example.aspectlogspringbootstarter.aop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/25 22:07
 * @since 0.0.1
 */
@Component
@ConfigurationProperties(prefix = "aspect.log")
public class AspectLogProperties {
    private boolean enable;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
