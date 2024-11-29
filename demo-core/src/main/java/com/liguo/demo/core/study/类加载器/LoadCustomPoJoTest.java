package com.liguo.demo.core.study.类加载器;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/15 23:17
 * @since 0.0.1
 */
public class LoadCustomPoJoTest {
    public static void main(String[] args) throws Exception {
        // 初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父类加载器设置为应用程序类加载器AppClassLoade
        CustomClassLoader classLoader = new CustomClassLoader(
                "E:\\workspaces\\demo\\demo-core\\src");
        // 从磁盘中创建一个目录，将要加载的类的class放入目录
        // Class.forName效果和classLoader.loadClass一致
        Class<?> clazz = Class.forName("com.liguo.demo.core.study.类加载器.TestUser", true, classLoader);
        Object obj = clazz.newInstance();
        System.out.println(obj.toString());
        System.out.println(clazz.getClassLoader());
        System.out.println();
        CustomClassLoader classLoader2 = new CustomClassLoader(
                "E:\\workspaces\\demo\\demo-core\\src");
        Class clazz1 = Class.forName("com.liguo.demo.core.study.类加载器.repeat.TestUser", true, classLoader2);
        Object obj1 = clazz1.newInstance();
        System.out.println(obj1.toString());
        System.out.println(clazz1.getClassLoader());
    }
}
