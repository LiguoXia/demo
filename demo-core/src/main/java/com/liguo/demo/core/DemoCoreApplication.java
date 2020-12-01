package com.liguo.demo.core;

import com.liguo.demo.core.pojo.converter.CarConverter;
import com.liguo.demo.core.pojo.entity.CarDO;
import com.liguo.demo.core.pojo.dto.CarDTO;
import com.liguo.demo.core.pojo.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@Slf4j
@MapperScan(basePackages = "com.liguo.demo.core.dao")
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
	public Result status() {
		CarDO carDO = new CarDO();
		carDO.setId1(1);
		carDO.setBrand("BWM");
		carDO.setName("宝马");
		CarDTO carDTO = CarConverter.INSTANCE.carDO2DTO(carDO);
		return Result.success(carDTO);
	}

}
