package com.liguo.demo.core.study;

import cn.hutool.core.util.ObjectUtil;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/4/3 14:41
 * @since 0.0.1
 */
@Slf4j
public class eq {

    public static void main(String[] args) {
        longEq();
    }

    private static void longEq() {
        Long a = 1L;
        Long d = 1L;
        Long b = 5555L;
        Long c = 5555L;
        log.info("5555L == b {}", c == b); // false
        log.info("5555L == b {}", ObjectUtil.equal(5555l, c)); // true
        // Long中有一个静态的内部类LongCache，专门用于缓存-128至127之间的值，一共256个元素
        log.info("5555L == b {}", a == d); // true
        log.info("5555L == b {}", ObjectUtil.equal(1l, d)); // true


    }

    public RequestInterceptor hh(String a) {
        return template -> {};
    }

    public static class Test{

    }
}
