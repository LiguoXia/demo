package com.liguo.demo.core.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/12/4 20:57
 * @since 0.0.1
 */
@Slf4j
public class AmountUtil {
    public static void main(String[] args) {
        BigDecimal a = new BigDecimal(0.1);
        log.info("a:{}", a);
    }
}
