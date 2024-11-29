package com.liguo.demo.gateway.route.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liguo.demo.gateway.route.dto.TRouteRuleQuery;
import com.liguo.demo.gateway.route.dto.TRouteRuleVO;
import com.liguo.demo.gateway.route.entity.TRouteRule;
import com.liguo.demo.gateway.route.mapper.TRouteRuleMapper;
import com.liguo.demo.gateway.route.service.ITRouteRuleService;
import com.liguo.demo.tool.pojo.vo.PageQueryReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author joe
 * @since 2023-09-22
 */
@Service
public class TRouteRuleServiceImpl extends ServiceImpl<TRouteRuleMapper, TRouteRule> implements ITRouteRuleService {

    @Autowired
    private TRouteRuleMapper mapper;

    @Override
    public Page<TRouteRuleVO> selectByPage(PageQueryReq<TRouteRuleVO, TRouteRuleQuery> req) {
        Page<TRouteRuleVO> page = req.newPage();
        List<TRouteRuleVO> list = mapper.selectByPage(page, req.getCondition());
        page.setRecords(list);
        return page;
    }
}
