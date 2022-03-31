package com.liguo.demo.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liguo.demo.core.pojo.entity.Cron;
import com.liguo.demo.core.pojo.entity.ThreeFlow;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资金计划系统参数表 Mapper 接口
 * </p>
 *
 * @author liguo
 * @since 2020-12-14
 */
public interface CronMapper extends BaseMapper<Cron> {

    List<Cron> getAll();
}
