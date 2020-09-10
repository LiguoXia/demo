package com.liguo.demo.getway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class DemoGetwayApplication {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		SpringApplication app = new SpringApplication(DemoGetwayApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		log.info("^-^####################################################^-^");
		log.info("^-^##### Getway service was started,{} senconds.#####^-^", System.currentTimeMillis() - startTime);
		log.info("^-^####################################################^-^");
	}

	@GetMapping("/status")
	public String status() {
		return "Service is ok";
	}
}
