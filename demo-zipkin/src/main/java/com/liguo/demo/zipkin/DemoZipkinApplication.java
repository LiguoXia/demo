package com.liguo.demo.zipkin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zipkin2.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@RestController
@Slf4j
public class DemoZipkinApplication {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		SpringApplication app = new SpringApplication(DemoZipkinApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		log.info("^-^####################################################^-^");
		log.info("^-^##### Zipkin service was started,{} senconds.#####^-^", System.currentTimeMillis() - startTime);
		log.info("^-^####################################################^-^");
	}

	@GetMapping("/status")
	public String status() {
		return "ok";
	}

}
