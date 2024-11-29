package com.liguo.demo.core.study.反射与代理.jdk动态代理;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 21:49
 * @since 0.0.1
 */
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("添加用户");
    }

    @Override
    public void updateUser(String str) {
        System.out.println("更新用户信息" + str);
    }
}
