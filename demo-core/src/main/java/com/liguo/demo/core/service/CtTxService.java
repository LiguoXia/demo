package com.liguo.demo.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liguo.demo.core.pojo.entity.Ct3;
import com.liguo.demo.core.pojo.entity.CtTx;

import java.util.List;

/**
 * <p>
 * 平台流水 服务类
 * </p>
 *
 * @author liguo
 * @since 2020-12-15
 */
public interface CtTxService extends IService<CtTx> {

    void importDate(List<CtTx> ctTxs);

    List<CtTx> getBy(String jiaoyirq, String jine, String dfkhh);

    void updateByXuhao(String xuhao,String otherXuhao);


}
