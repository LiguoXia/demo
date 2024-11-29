package com.liguo.demo.core.study.designpattern.strategy;

import org.springframework.stereotype.Service;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/29 20:55
 * @since 0.0.1
 */
@Service
public class OperationMultiply implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
