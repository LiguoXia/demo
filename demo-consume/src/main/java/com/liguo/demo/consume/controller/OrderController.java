package com.liguo.demo.consume.controller;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liguo.demo.consume.dao.OrderMapper;
import com.liguo.demo.consume.pojo.entity.Order;
import com.liguo.demo.consume.pojo.entity.OrderQuery;
import com.liguo.demo.consume.pojo.vo.PageQueryReq;
import com.liguo.demo.consume.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;

/**
 * 订单接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/29 16:42
 * @since 0.0.1
 */
@Api(tags = "订单接口")
@Slf4j
@RestController
@RequestMapping("/api/order")
public class OrderController extends HttpServlet {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    Snowflake snowflake = IdUtil.createSnowflake(1, 1);

    @ApiOperation("保存")
    @PostMapping("/save")
    public String testInsertOrder() {

        for (int i = 0; i < 10; i++) {
            UUID uuid = UUID.randomUUID();
            // 生成ID
            long id = snowflake.nextId() + 1;
            log.info("uuid:{},{}", uuid, uuid.hashCode());
            log.info("id:{}", id);
            orderMapper.insertOrder(id, 100 + i, "空调111", 10);
        }
        return "success";
    }

    @ApiOperation("获取")
    @PostMapping("/get")
    public Page<Order> getOrder(@Validated @RequestBody PageQueryReq<Order, OrderQuery> req) {
        return orderService.selectByPage(req);
    }

    static {
        log.info("1111111111111111111111111111111111111111111");
    }

    {
        log.info("22222222222222222222222222222222222222222222");
    }
}
