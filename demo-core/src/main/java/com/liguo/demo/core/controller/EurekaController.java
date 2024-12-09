package com.liguo.demo.core.controller;

import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation("根据serviceId获取所有实例信息")
    @GetMapping("/instances/{serviceId}")
    public List<ServiceInstance> getServiceInstances(@ApiParam("服务名") @PathVariable String serviceId) {
        return discoveryClient.getInstances(serviceId);
    }


    @Autowired
    private EurekaClient eurekaClient;

    /**
     * 获取指定服务的所有实例
     */
    @ApiOperation("根据serviceId获取所有实例信息2")
    @GetMapping("/instances2/{serviceId}")
    public List<InstanceInfo> getInstancesByServiceId(@ApiParam("服务名") @PathVariable String serviceId) {
        return eurekaClient.getInstancesByVipAddress(serviceId, false);
    }

    @Autowired
    private EurekaInstanceConfig eurekaInstanceConfig;

    /**
     * 获取当前实例的 instanceId
     */
    @ApiOperation("获取当前实例instanceId")
    @GetMapping("/getInstanceId")
    public String getInstanceId() {
        return eurekaInstanceConfig.getInstanceId();
    }
}
