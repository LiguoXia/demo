package com.liguo.demo.gateway.route.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liguo.demo.gateway.route.dto.TRouteRuleQuery;
import com.liguo.demo.gateway.route.dto.TRouteRuleVO;
import com.liguo.demo.gateway.route.entity.TRouteRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author joe
 * @since 2023-09-22
 */
@Mapper
public interface TRouteRuleMapper extends BaseMapper<TRouteRule> {

    /** 分页查询*/
    List<TRouteRuleVO> selectByPage(Page<TRouteRuleVO> page, @Param("req") TRouteRuleQuery req);
}
