package com.liguo.demo.core.study.interface4Param.lambda;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/17 9:52
 * @since 0.0.1
 */
public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        int result = calculator.calculate(5, 3, (a, b) -> a + b); // Lambda表达式
        System.out.println("Result: " + result);
    }
}
