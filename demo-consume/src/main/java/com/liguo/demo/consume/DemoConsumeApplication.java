package com.liguo.demo.consume;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.liguo.demo.consume.*")
@RestController
@Slf4j
public class DemoConsumeApplication {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		SpringApplication app = new SpringApplication(DemoConsumeApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		log.info("^-^####################################################^-^");
		log.info("^-^##### Consume service was started,{} senconds.#####^-^", System.currentTimeMillis() - startTime);
		log.info("^-^####################################################^-^");
	}

	@GetMapping("/status")
	public String status() {
		return "ok";
	}

}
