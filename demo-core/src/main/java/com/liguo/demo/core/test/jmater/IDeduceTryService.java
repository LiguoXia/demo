package com.liguo.demo.core.test.jmater;

import java.util.concurrent.ExecutionException;

/**
 * 推导试跑接口
 *
 * @author 01395755[xialiguo]
 * @version 4.4
 * @date 2022/3/22 15:08
 * @since 4.4
 */
public interface IDeduceTryService {

    /**
     * 推导试跑
     *
     * @param req 请求对象
     * @return 返回对象
     */
    DeduceTryResp deduceTry(DeduceTryReq req) throws ExecutionException, InterruptedException;
}