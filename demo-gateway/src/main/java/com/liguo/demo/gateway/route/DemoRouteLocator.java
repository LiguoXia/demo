package com.liguo.demo.gateway.route;

import cn.hutool.core.util.StrUtil;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.liguo.demo.gateway.route.entity.TRouteRule;
import com.liguo.demo.gateway.route.service.ITRouteRuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/1 14:04
 * @since 0.0.1
 */
@Slf4j
public class DemoRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {
    private static AtomicReference<Map<String, ZuulProperties.ZuulRoute>> routeRef = new AtomicReference<>();

    @Autowired
    private ITRouteRuleService routeRuleService;

    public DemoRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
    }

    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1, new ThreadFactoryBuilder()
                .setNameFormat("Beic-Gateway-Route-Locator-Thread" + "-%d")
                .setDaemon(true)
                .build());
        scheduler.scheduleAtFixedRate(() ->{
            // 从数据库加载最新全量路由信息
            refreshRoutesFromDB();
            // 刷新路由信息
            refresh();
        }, 10, 120, TimeUnit.SECONDS);
    }

    private void refreshRoutesFromDB() {
        log.info("从DB刷新路由配置开始");
        long start = System.currentTimeMillis();
        List<TRouteRule> routeRules = routeRuleService.list();
        // 先清除原有数据
        Map<String, ZuulProperties.ZuulRoute> dataMap = new HashMap<>(routeRules.size());
        routeRules.forEach(r -> {
            Set<String> sensitiveHeaderSet = new HashSet<>();
            String sensitiveHeaders = r.getSensitiveHeaders();
            if(StrUtil.isNotBlank(sensitiveHeaders)) {
                String[] headers = sensitiveHeaders.split(",");
                sensitiveHeaderSet.addAll(Arrays.asList(headers));
            }
            ZuulProperties.ZuulRoute zuulRoute = new ZuulProperties.ZuulRoute(r.getRouteId(), r.getPath(), r.getServiceId(), r.getUrl(),
                    r.getStripPrefix(), r.getRetryable(), sensitiveHeaderSet);
            dataMap.put(r.getPath(), zuulRoute);
        });
        routeRef.set(dataMap);
        log.info("从DB刷新路由配置路由条数完成");
    }

    @Override
    protected LinkedHashMap<String, ZuulProperties.ZuulRoute> locateRoutes() {
        log.info("执行:locateRoutes");
        refreshRoutesFromDB();
        Map<String, ZuulProperties.ZuulRoute> routeMap = routeRef.get();
        if(routeMap == null) {
            return new LinkedHashMap<>();
        }
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>(routeMap.size());
        values.putAll(routeMap);
        return values;
    }

    @Override
    public void refresh() {
        doRefresh();
    }
}
