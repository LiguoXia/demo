package com.liguo.demo.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liguo.demo.core.pojo.entity.ThreeFlow;

import java.util.List;

/**
 * <p>
 * 资金计划系统参数表 服务类
 * </p>
 *
 * @author liguo
 * @since 2020-12-14
 */
public interface IThreeFlowService extends IService<ThreeFlow> {

    void importDate(List<ThreeFlow> threeFlowList);

    void delete();
}
