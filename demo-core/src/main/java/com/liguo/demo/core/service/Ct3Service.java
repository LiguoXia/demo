package com.liguo.demo.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liguo.demo.core.pojo.entity.Ct3;
import com.liguo.demo.core.pojo.entity.PlatformFlow;

import java.util.List;

/**
 * <p>
 * 平台流水 服务类
 * </p>
 *
 * @author liguo
 * @since 2020-12-15
 */
public interface Ct3Service extends IService<Ct3> {

    void importDate(List<Ct3> ct3s);

    List<Ct3> getAll();

    void updateByXuhao(String xuhao,String otherXuhao);

    void chcck();
}
