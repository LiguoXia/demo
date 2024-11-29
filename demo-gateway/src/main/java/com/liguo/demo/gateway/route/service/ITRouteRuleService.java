package com.liguo.demo.gateway.route.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liguo.demo.gateway.route.dto.TRouteRuleQuery;
import com.liguo.demo.gateway.route.dto.TRouteRuleVO;
import com.liguo.demo.gateway.route.entity.TRouteRule;
import com.liguo.demo.tool.pojo.vo.PageQueryReq;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author joe
 * @since 2023-09-22
 */
public interface ITRouteRuleService extends IService<TRouteRule> {

    /** 分页查询*/
    Page<TRouteRuleVO> selectByPage(PageQueryReq<TRouteRuleVO, TRouteRuleQuery> req);
}
