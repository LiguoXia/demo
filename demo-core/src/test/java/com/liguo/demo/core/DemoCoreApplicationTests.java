package com.liguo.demo.core;

import com.liguo.demo.core.dao.CronMapper;
import com.liguo.demo.core.pojo.entity.Cron;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoCoreApplicationTests {
	@Autowired
	private CronMapper cronMapper;

	@Test
	void contextLoads() {
		List<Cron> a = cronMapper.getAll();
		String cron = a.get(0).getCron();
		System.out.println(cron + "----");
	}
}
