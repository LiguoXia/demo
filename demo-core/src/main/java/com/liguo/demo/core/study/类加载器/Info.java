package com.liguo.demo.core.study.类加载器;

import lombok.extern.slf4j.Slf4j;

/**
 * Bootstrap ClassLoader 是由C++实现的 java没有对应类
 *
 * // 双亲委派实现 
 *  {@link ClassLoader#loadClass(String)}
 *
 *  双亲委派机制过程？
 *  当AppClassLoader加载一个class时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器ExtClassLoader去完成。
 *  当ExtClassLoader加载一个class时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给BootStrapClassLoader去完成。
 *  如果BootStrapClassLoader加载失败(例如在$JAVA_HOME/jre/lib里未查找到该class)，会使用ExtClassLoader来尝试加载；
 *  若ExtClassLoader也加载失败，则会使用AppClassLoader来加载，
 *  如果AppClassLoader也加载失败，则会报出异常ClassNotFoundException。

 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/15 21:09
 * @since 0.0.1
 */
@Slf4j
public class Info {
    public static void main(String[] args) {
        // $JAVA_HOME/jre/lib 目录中的 jar 包

        // D:\systemSoft\java\jdk1.8.0_172\jre\lib\resources.jar;
        // D:\systemSoft\java\jdk1.8.0_172\jre\lib\rt.jar;
        // D:\systemSoft\java\jdk1.8.0_172\jre\lib\sunrsasign.jar;
        // D:\systemSoft\java\jdk1.8.0_172\jre\lib\jsse.jar;
        // D:\systemSoft\java\jdk1.8.0_172\jre\lib\jce.jar;
        // D:\systemSoft\java\jdk1.8.0_172\jre\lib\charsets.jar;
        // D:\systemSoft\java\jdk1.8.0_172\jre\lib\jfr.jar;
        // D:\systemSoft\java\jdk1.8.0_172\jre\classes
        log.info("Bootstrap ClassLoader 所加载的目录:{}", System.getProperty("sun.boot.class.path"));
        Test2 test2 = new Test2();
        // ExtClassLoader
        // D:\systemSoft\java\jdk1.8.0_172\jre\lib\ext;
        // C:\WINDOWS\Sun\Java\lib\ext
        log.info("ExtClassLoader 所加载的目录:{}", System.getProperty("java.ext.dirs"));
    }
}
