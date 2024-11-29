package com.liguo.demo.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
// 该注解声明这是一个zuul代理，该代理使用Ribbon来定位注册到Eureka Server上的微服务，同时，整合了hystrix，实现了容错。
@EnableZuulProxy
// 网关不需要
//@EnableBaseFeignInterceptor
// 开启数据监控注解
@EnableHystrixDashboard
public class DemoGatewayApplication {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		SpringApplication app = new SpringApplication(DemoGatewayApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		log.info("^-^####################################################^-^");
		log.info("^-^##### Gateway service was started,{} senconds.#####^-^", System.currentTimeMillis() - startTime);
		log.info("^-^####################################################^-^");
	}

	@GetMapping("/status")
	public String status() {
		return "Service is ok";
	}
}
