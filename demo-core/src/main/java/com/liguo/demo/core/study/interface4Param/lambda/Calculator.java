package com.liguo.demo.core.study.interface4Param.lambda;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/17 9:52
 * @since 0.0.1
 */
public class Calculator {
    public int calculate(int a, int b, Operation operation) {
        return operation.apply(a, b);
    }
}
