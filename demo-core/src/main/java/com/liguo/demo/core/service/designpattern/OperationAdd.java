package com.liguo.demo.core.service.designpattern;

import org.springframework.stereotype.Service;

/**
 * 加法
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2021/6/29 20:52
 * @since 0.0.1
 */
@Service
public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
