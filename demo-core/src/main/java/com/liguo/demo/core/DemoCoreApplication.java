package com.liguo.demo.core;

import com.liguo.demo.core.pojo.covert.CarCovert;
import com.liguo.demo.core.pojo.dos.CarDO;
import com.liguo.demo.core.pojo.dto.CarDTO;
import com.liguo.demo.core.pojo.vo.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@RestController
@Slf4j
public class DemoCoreApplication {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		SpringApplication app = new SpringApplication(DemoCoreApplication.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
		log.info("^-^####################################################^-^");
		log.info("^-^##### Core service was started,{} senconds.#####^-^", System.currentTimeMillis() - startTime);
		log.info("^-^####################################################^-^");
	}

	@GetMapping("/status")
	public HttpResult status() {
		CarDO carDO = new CarDO();
		carDO.setId1(1);
		carDO.setBrand("BWM");
		carDO.setName("宝马");
		CarDTO carDTO = CarCovert.INSTANCE.carDO2DTO(carDO);
		return HttpResult.success(carDTO);
	}

}
