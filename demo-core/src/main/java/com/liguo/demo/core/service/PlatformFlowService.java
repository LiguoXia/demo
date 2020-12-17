package com.liguo.demo.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liguo.demo.core.pojo.entity.PlatformFlow;
import com.liguo.demo.core.pojo.entity.ThreeFlow;

import java.util.List;

/**
 * <p>
 * 平台流水 服务类
 * </p>
 *
 * @author liguo
 * @since 2020-12-15
 */
public interface PlatformFlowService extends IService<PlatformFlow> {

    void importDate(List<PlatformFlow> threeFlowList);
}
