package com.liguo.demo.gateway.controller;

import com.liguo.demo.gateway.route.DemoRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手动刷新路由
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/1 14:24
 * @since 0.0.1
 */
@RestController
public class ZuulRouteRefreshController {
    @Autowired
    private ZuulHandlerMapping zuulHandlerMapping;

    @Autowired
    private DemoRouteLocator routeLocator;

    @PostMapping("/routes/refresh")
    public String refreshRoutes() {
        routeLocator.refresh();
        zuulHandlerMapping.setDirty(true);  // 标记路由表为脏，触发刷新
        return "Routes refreshed";
    }
}
