package com.liguo.demo.consume;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liguo.demo.consume.dao.CronMapper;
import com.liguo.demo.consume.dao.OrderMapper;
import com.liguo.demo.consume.pojo.entity.Cron;
import com.liguo.demo.consume.pojo.entity.CronQuery;
import com.liguo.demo.consume.pojo.entity.Order;
import com.liguo.demo.consume.pojo.vo.PageQueryReq;
import com.liguo.demo.consume.service.CronService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class DemoConsumeApplicationTests {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CronMapper cronMapper;

    @Autowired
    private CronService cronService;

    @Test
    void contextLoads() {
        log.info("结果:{}", orderMapper.selectByPage(null, null));

        List<Order> order = orderMapper.selectList(null);
        log.info("order:{}", order);
    }

    @Test
    void pageOrder() {
        IPage page = new Page(1, 2);
        orderMapper.selectPage(page, null);
    }

    @Test
    void pageCron() {
        IPage page = new Page(1, 2);
        cronMapper.selectPage(page, null);
    }

    @Test
    void queryCron() {
        String cronId = "1";
        LambdaQueryWrapper<Cron> lqw = new LambdaQueryWrapper<Cron>();
        lqw.eq(StringUtils.isNotBlank(cronId), Cron::getCronId, cronId);
        cronMapper.selectList(lqw);
    }

    @Test
    void queryPageCron() {
        PageQueryReq<Cron, CronQuery> req = new PageQueryReq<>();
        req.setCurrent(2);
        req.setSize(2);
        Page<Cron> page = cronService.selectByPage(req);
        Page<Cron> page1 = cronService.selectByPage(req);
        log.info("page11111:{}", JSON.toJSONString(page));
    }

    @Test
    void getById() {
        Cron corn = cronService.getById1(3L);
        Cron corn1 = cronService.getById1(3L);
        log.info("corn:{}", JSON.toJSONString(corn));
    }

}
