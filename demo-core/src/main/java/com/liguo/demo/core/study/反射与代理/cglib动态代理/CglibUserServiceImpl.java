package com.liguo.demo.core.study.反射与代理.cglib动态代理;

/**
 * 被cglib代理的类
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 22:47
 * @since 0.0.1
 */
public class CglibUserServiceImpl {
    public String addUser() {
        System.out.println("添加了一个用户");
        return "返回";
    }

    public void deleteUser() {
        System.out.println("删除了一个用户");
    }
}
