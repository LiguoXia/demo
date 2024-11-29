package com.liguo.demo.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liguo.demo.core.pojo.entity.Cron;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 资金计划系统参数表 Mapper 接口
 * </p>
 *
 * @author liguo
 * @since 2020-12-14
 */
@Mapper
public interface CronMapper extends BaseMapper<Cron> {

    List<Cron> getAll();
}
