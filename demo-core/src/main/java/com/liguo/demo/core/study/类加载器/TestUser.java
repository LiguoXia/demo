package com.liguo.demo.core.study.类加载器;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/15 23:15
 * @since 0.0.1
 */
public class TestUser {
    private String name;

    public TestUser() {
    }

    public TestUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "========这是User测试文件1号========";
    }
}
