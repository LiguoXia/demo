package com.liguo.demo.core.study.classloader.case1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/29 11:24
 * @since 0.0.1
 */
public class MyClassLoader extends ClassLoader {
    // 指定类文件存放的目录
    // private String classDir;

    /**
     * 存储待加载的类的全限定类名和该类在磁盘上的路径的对应关系
     * com.liguo.demo.core.study.classloader.case1.NeedLoadClass1 : D:/class/NeedLoadClass1.class
     */
    public static Map<String, String> classMap = new HashMap<>();

   /* public MyClassLoader(String classDir) {
        this.classDir = classDir;
    }*/

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("加载日志,判断是否有缓存");
        // 将类的包名中的 "." 替换为文件路径的 "/"，并添加 .class 扩展名
        // String fileName = classDir + name.replace('.', File.separatorChar) + ".class";
        String fileName = classMap.get(name);
        byte[] classData = loadClassData(fileName);

        if (classData == null) {
            throw new ClassNotFoundException("Class not found: " + name);
        }

        // 调用 defineClass 方法将字节码转换为 Class 对象
        return defineClass(name, classData, 0, classData.length);
    }

    private byte[] loadClassData(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            // 获取文件长度并创建对应大小的字节数组
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer); // 将文件读取到字节数组中
            return buffer;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
