package com.liguo.demo.core.test.jdbctemplate;

import com.alibaba.fastjson.JSON;
import com.liguo.demo.core.pojo.entity.Cron;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/25 22:37
 * @since 0.0.1
 */
@Slf4j
@Service
public class JdbcTemplateTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Cron> getAll() {
        List<Cron> crons = jdbcTemplate.query("select * from cron", (resultSet, i) -> {
            Cron cron = new Cron();
            cron.setCronId(resultSet.getString("cron_Id"));
            cron.setCron(resultSet.getString("cron"));
            return cron;
        });
        List<Cron> cron1 = jdbcTemplate.query("select * from cron", BeanPropertyRowMapper.newInstance(Cron.class));
        log.info("cron：{}", JSON.toJSONString(cron1));
        return crons;
    }
}
