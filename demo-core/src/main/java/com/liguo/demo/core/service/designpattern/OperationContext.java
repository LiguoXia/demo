package com.liguo.demo.core.service.designpattern;

/**
 * 策略上下文
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/29 21:27
 * @since 0.0.1
 */
public class OperationContext {
    private Strategy strategy;

    public OperationContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public int contextMethod(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
