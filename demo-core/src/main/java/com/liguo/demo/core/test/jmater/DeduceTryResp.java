package com.liguo.demo.core.test.jmater;

import lombok.Data;

/**
 * 推导试跑返回对象
 *
 * @author 01395755[xialiguo]
 * @version 4.4
 * @date 2022/3/22 15:09
 * @since 4.4
 */
@Data
public class DeduceTryResp {
    /**
     * 推导编号
     */
    private Long deductionNo;

    /**
     * 推导状态, 1-推导成功; 2-推导失败; 3-后台运行
     */
    private Integer deduceStatus;

    /**
     * 消息
     */
    private String deduceMsg;
}