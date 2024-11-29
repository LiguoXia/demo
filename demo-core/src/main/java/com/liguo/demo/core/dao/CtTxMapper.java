package com.liguo.demo.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liguo.demo.core.pojo.entity.CtTx;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 平台流水 Mapper 接口
 * </p>
 *
 * @author liguo
 * @since 2020-12-15
 */
@Mapper
public interface CtTxMapper extends BaseMapper<CtTx> {
    int batchSave(@Param("list") List<CtTx> ctTxs);
}
