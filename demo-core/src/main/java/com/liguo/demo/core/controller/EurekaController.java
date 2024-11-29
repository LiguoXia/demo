package com.liguo.demo.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/5 11:49
 * @since 0.0.1
 */
@Api(tags = "Eureka接口")
@RestController
@Slf4j
public class EurekaController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/instances/{serviceName}")
    public List<ServiceInstance> getServiceInstances(@ApiParam("服务名") @PathVariable String serviceName) {
        return discoveryClient.getInstances(serviceName);
    }
}
