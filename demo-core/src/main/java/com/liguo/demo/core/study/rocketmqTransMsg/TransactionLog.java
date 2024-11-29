package com.liguo.demo.core.study.rocketmqTransMsg;

import lombok.Data;

/**
 * 事务日志表,用来绑定本地事务,判断事务是否成功
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/9 16:49
 * @since 0.0.1
 */
@Data
public class TransactionLog {
    private String id;
    private String business;
    private String foreignKey;
}
