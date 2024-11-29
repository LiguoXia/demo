package com.liguo.demo.core.study.反射与代理;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 15:05
 * @since 0.0.1
 */
@Slf4j
@Data
public class 被代理类 {

    public 被代理类(String str, String str1) {
        this.str = str;
        this.str1 = str1;
    }

    public 被代理类() {

    }

    public String str = "1";
    private String str1 = "2";

    public String say(String a) {
        log.info("public hello world {}", a);
        return "public hello world " + a;
    }

    private String say1(String a) {
        log.info(" private hello world {}", a);
        return "private hello world " + a;
    }
}
