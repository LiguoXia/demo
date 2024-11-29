package com.liguo.demo.core.study.类加载器;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/15 23:16
 * @since 0.0.1
 */
public class TestService {
    public String testPrint(String name) {
        String result = name + "调用当前方法";
        System.out.println(name + "调用当前方法");
        return result;
    }
}
