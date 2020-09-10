package com.liguo.demo.consume.fegin.client;

import com.liguo.demo.consume.pojo.vo.HttpResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * desc:
 *
 * @author liguo
 * @create 2020-09-09
 */
@FeignClient(name = "demo-core")
@RequestMapping("/aa/bb")
public interface TestFeginClient {
    @PostMapping("/test")
    HttpResult test(@RequestParam("name") String name);
}
