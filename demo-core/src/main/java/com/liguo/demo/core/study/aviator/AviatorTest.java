package com.liguo.demo.core.study.aviator;

import com.googlecode.aviator.AviatorEvaluator;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/9 15:21
 * @since 0.0.1
 */
public class AviatorTest {
    public static void main(String[] args) {


        BigInteger a = new BigInteger(String.valueOf(Long.MAX_VALUE) + String.valueOf(Long.MAX_VALUE));
        BigDecimal b = new BigDecimal("3.2");
        BigDecimal c = new BigDecimal("9999.99999");
        Object rt = AviatorEvaluator.exec("360*(2+3)/2/3");
        System.out.println(rt + " " + rt.getClass());

    }
}
