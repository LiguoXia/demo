package com.liguo.demo.consume.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liguo.demo.consume.pojo.entity.Cron;
import com.liguo.demo.consume.pojo.entity.CronQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/4 16:50
 * @since 0.0.1
 */
@Mapper
public interface CronMapper extends BaseMapper<Cron> {

    /**
     * 分页查询
     */
    List<Cron> selectByPage(Page<Cron> page, @Param("req") CronQuery req);
}
