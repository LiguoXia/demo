package com.liguo.demo.core.study.rocketmqTransMsg;

/**
 * 日志服务接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/9 16:45
 * @since 0.0.1
 */
public interface TransactionLogService {

    int get(String transactionId);
}
