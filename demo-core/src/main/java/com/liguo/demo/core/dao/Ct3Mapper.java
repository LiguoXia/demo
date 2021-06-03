package com.liguo.demo.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liguo.demo.core.pojo.entity.Ct3;
import com.liguo.demo.core.pojo.entity.PlatformFlow;
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
public interface Ct3Mapper extends BaseMapper<Ct3> {
    int batchSave(@Param("list") List<Ct3> ct3s);
}
