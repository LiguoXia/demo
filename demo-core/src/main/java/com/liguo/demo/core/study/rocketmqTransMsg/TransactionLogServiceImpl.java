package com.liguo.demo.core.study.rocketmqTransMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 日志服务实现
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/9 16:55
 * @since 0.0.1
 */
@Service
public class TransactionLogServiceImpl implements TransactionLogService{
    @Autowired
    private TransactionLogMapper mapper;
    @Override
    public int get(String transactionId) {
        return mapper.get(transactionId);
    }
}
