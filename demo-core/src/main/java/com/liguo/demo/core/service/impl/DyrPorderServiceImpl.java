package com.liguo.demo.core.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liguo.demo.core.dao.DyrPorderMapper;
import com.liguo.demo.core.pojo.entity.DyrPorder;
import com.liguo.demo.core.service.IDyrPorderService;
import com.liguo.demo.core.service.superman.ClientTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author liguo
 * @since 2023-02-27
 */
@Slf4j
@Service
public class DyrPorderServiceImpl extends ServiceImpl<DyrPorderMapper, DyrPorder> implements IDyrPorderService {
    @Autowired
    private ClientTest clientTest;

    @Override
    public List<DyrPorder> getAll() throws UnsupportedEncodingException {
        LambdaQueryChainWrapper<DyrPorder> queryChainWrapper = this.lambdaQuery();
        queryChainWrapper.eq(DyrPorder::getOrderNumber, "OKL23022743170")
                .eq(DyrPorder::getStatus, "2");
        List<DyrPorder> dyrPorders = queryChainWrapper.list();
        log.info("订单信息");
        for (DyrPorder dyrPorder : dyrPorders) {
            log.info("订单订单号:{},充值号码:{},充值金额:{}", dyrPorder.getOrderNumber(), dyrPorder.getMobile(), dyrPorder.getProductName());
            clientTest.get(dyrPorder.getOrderNumber(), dyrPorder.getMobile(), dyrPorder.getProductName());
        }
        return dyrPorders;
    }
}
