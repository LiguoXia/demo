package com.liguo.demo.core.test.referenceorvalue;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/28 9:46
 * @since 0.0.1
 */
@Slf4j
public class StringTest {

    public static void main(String[] args) {
        String str = "123";
        modifyStr(str);
        log.info("str值:{}", str); // str值:123
        Integer integer = 1;
        modifyInt(integer);
        log.info("int值:{}", integer); // str值:1
    }

    private static void modifyStr(String str) {
        str = "456";
    }

    private static void modifyInt(Integer integer) {
        integer = 4;
    }
}
