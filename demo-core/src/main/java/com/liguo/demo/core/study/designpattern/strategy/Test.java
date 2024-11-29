package com.liguo.demo.core.study.designpattern.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/29 22:22
 * @since 0.0.1
 */
@Slf4j
public class Test {

    public static void main(String[] args) {
        Strategy strategy = new OperationAdd();

        OperationContext operationContext = new OperationContext(strategy);
        int a = operationContext.contextMethod(1, 2);
        log.info("结果：{}", a);
    }
}
