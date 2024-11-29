package com.liguo.demo.consume.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liguo.demo.consume.pojo.entity.Order;
import com.liguo.demo.consume.pojo.entity.OrderQuery;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/29 16:42
 * @since 0.0.1
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 新增订单
     */
    @Insert("INSERT INTO `t_order` (order_id, user_id,product_name,COUNT) VALUES(#{order_id}, 1001,#{product_name},10)")
    int insertOrder(@Param("order_id") long order_id, @Param("user_id") int user_id, @Param("product_name") String product_name, @Param("count") int count);

    /**
     * 分页查询
     */
    List<Order> selectByPage(Page<Order> page, @Param("req") OrderQuery req);
}
