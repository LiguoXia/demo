package com.liguo.demo.consume.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liguo.demo.consume.pojo.entity.Order;
import com.liguo.demo.consume.pojo.entity.OrderQuery;
import com.liguo.demo.consume.pojo.vo.PageQueryReq;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/2 10:21
 * @since 0.0.1
 */
public interface OrderService extends IService<Order> {
    /**
     * 分页查询
     */
    Page<Order> selectByPage(PageQueryReq<Order, OrderQuery> req);
}
