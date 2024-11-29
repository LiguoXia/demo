package com.liguo.demo.consume.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liguo.demo.consume.dao.OrderMapper;
import com.liguo.demo.consume.pojo.entity.Order;
import com.liguo.demo.consume.pojo.entity.OrderQuery;
import com.liguo.demo.consume.pojo.vo.PageQueryReq;
import com.liguo.demo.consume.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/2 10:22
 * @since 0.0.1
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    /**
     * 分页查询
     *
     * @param page
     * @param req
     */
    @Override
    public Page<Order> selectByPage(PageQueryReq<Order, OrderQuery> req) {
        Page<Order> page = req.newPage();
        List<Order> list = orderMapper.selectByPage(page, req.getCondition());
        page.setRecords(list);
        return page;
    }
}
