package com.liguo.demo.gateway.config;

import com.liguo.demo.gateway.route.DemoRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Descritption: zuul网关配置类
 *
 * @author 01392677
 * @date 2020/04/02
 */
@Configuration
public class ZuulRouteLocatorConfig {

    @Autowired
    protected ZuulProperties zuulProperties;

    @Autowired
    protected ServerProperties server;

    @Bean
    public DemoRouteLocator beicRouteLocator() {
        return new DemoRouteLocator(this.server.getServlet().getContextPath(), this.zuulProperties);
    }
}
