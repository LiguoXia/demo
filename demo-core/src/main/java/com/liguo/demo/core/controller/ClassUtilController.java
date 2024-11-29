package com.liguo.demo.core.controller;

import com.liguo.demo.core.study.classloader.UsedClass;
import com.liguo.demo.core.study.classloader.case1.MyClassLoader;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Class工具接口
 *
 * 如果你引入了一个 JAR 文件，例如 example-library.jar，而这个库中有很多类，但你只使用了其中的一个或两个类，只有这几个类会被加载到内存中，其他未被引用的类则不会被加载。
 * @ConditionalOnClass 注解用于条件性地启用某个 Spring Bean，前提是指定的类在类路径上可用。它的主要作用是检查某个特定类是否存在，而不需要将该类加载为 Bean。
 *
 * 哪些算引用:
 * 1、通过 @Component, @Service, @Repository, @Controller 等注解
 * 2、@Configuration 类中的 @Bean 方法定义的类
 * 3、@Autowired注入
 * 4、代码中显式创建或调用，例如使用 new 关键字创建的对象
 * 5、通过 AOP 切面：如果一个类被 AOP 切面（如通过 @Aspect、@Around 等注解）代理或拦截
 *
 *
 *
 * 1. 类加载器的基本概念
 * 在 Java 中，类加载器遵循双亲委派机制（Parent Delegation Model），它确保类的加载顺序和一致性。类加载器有三个主要职责：
 *
 * 加载类：从文件系统、JAR 包、网络或其他来源加载 .class 文件，并将其转换为 JVM 能识别的二进制格式。
 * 连接类：包括验证、准备和解析三个步骤，用于确保类的正确性和可用性。
 * 初始化类：为类的静态变量赋值，执行静态代码块等。
 *
 * 2. 类加载器的类型
 * Java 中有三种主要的类加载器：
 *
 * 2.1. Bootstrap ClassLoader（启动类加载器）
 * 作用：加载 Java 核心类库（如 rt.jar 中的类）。它是 JVM 内部的类加载器，负责加载 JDK 提供的核心类库，如 java.lang.*、java.util.*。
 * 特点：它是用本地代码（如 C/C++）实现的，不是 Java 类的实例，用户无法直接引用它。
 * 2.2. Extension ClassLoader（扩展类加载器）
 * 作用：加载扩展库路径中的类，例如 JRE 的 lib/ext 目录下的类或 JDK 的 -Djava.ext.dirs 参数指定的目录。
 * 特点：扩展类加载器是用 Java 代码实现的，可以通过 ClassLoader.getParent() 获取到它。
 * 2.3. Application ClassLoader（系统类加载器）
 * 作用：加载应用程序类路径（classpath）下的类，通常是用户定义的类和第三方库。
 * 特点：它是最常用的类加载器，可以通过 ClassLoader.getSystemClassLoader() 获得。
 * 2.4. 自定义类加载器
 * 作用：在特殊情况下，用户可以通过继承 ClassLoader 类来定义自己的类加载器，以实现类的自定义加载行为，如从数据库、网络等地方加载类。
 * 应用场景：
 * 动态加载远程类
 * 通过不同的类加载器实现类的隔离（如 Tomcat、OSGi 模块化系统）
 * 加载加密的类文件
 *
 *
 * 3. 双亲委派模型
 * Java 的类加载器采用 双亲委派模型（Parent Delegation Model），其基本原理是：
 *
 * 每个类加载器都有一个父加载器（除启动类加载器外）。
 * 当类加载器试图加载一个类时，首先会将该请求委派给父加载器。如果父加载器能够加载该类，则直接返回。
 * 如果父加载器不能加载该类，则由当前类加载器尝试加载该类。
 * 双亲委派模型的好处：
 * 避免类的重复加载：通过委派机制，父类加载器加载的类不会被子类加载器重复加载，确保同一个类在 JVM 中只被加载一次。
 * 保证 Java 核心类的安全：通过这种机制，应用类加载器无法覆盖和替换 JDK 核心类库，如 java.lang.Object。
 *
 * 4. 类加载的流程
 * 类加载通常分为三个阶段：
 *
 * 4.1. 加载（Loading）
 * 类加载器根据指定的类名，找到 .class 文件，将其内容读取到内存中，并将其转换为 Class 对象。
 * 类加载器会首先检查该类是否已经被加载，如果已经被加载，则直接返回 Class 对象。
 * 4.2. 链接（Linking）
 * 链接过程包括以下三个步骤：
 *
 * 验证（Verification）：确保 .class 文件的字节码符合 JVM 规范，保证类的正确性。
 * 准备（Preparation）：为类的静态字段分配内存，并将其初始化为默认值。
 * 解析（Resolution）：将符号引用转换为直接引用，如将常量池中的类、方法、字段的符号引用解析为内存地址。
 * 4.3. 初始化（Initialization）
 * 初始化阶段会执行类的静态初始化代码块，并为类的静态变量赋值。
 *
 * 5. 类加载器的应用场景
 * 5.1. Web 容器中的类加载器
 * 在 Web 容器（如 Tomcat、Jetty）中，每个 Web 应用都有自己的类加载器，以确保各个应用的类互相隔离。这使得不同 Web 应用可以使用相同的类库而不会发生冲突。
 *
 * 5.2. OSGi 模块化系统
 * OSGi 是一种支持模块化开发的框架，它允许开发者在运行时动态安装、更新、卸载模块（称为 bundle）。每个 bundle 都有自己独立的类加载器，保证不同模块之间的类隔离。
 *
 * 5.3. JDBC 驱动的加载
 * JDBC 驱动使用了 ServiceLoader 机制来查找和加载具体的数据库驱动类。通常，应用程序的类加载器负责加载 JDBC 驱动类。
 *
 * 5.4. 自定义类加载器
 * 自定义类加载器常用于：
 *
 * 动态加载：在运行时从不同源加载类（如从网络或数据库）。
 * 类隔离：在插件系统或模块化系统中，不同模块使用不同的类加载器进行隔离。
 * 5.5. 热加载和卸载
 * 自定义类加载器允许实现类的热加载与卸载，例如在开发环境中通过热部署重新加载修改过的类，或者在生产环境中动态更新类。
 *
 * 6. 自定义类加载器的实现
 * @see MyClassLoader
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/29 16:42
 * @since 0.0.1
 */
@Api(tags = "Class工具接口")
@Slf4j
@RestController
@RequestMapping("/classUtil")
public class ClassUtilController {

    @ApiOperation("根据包名获取所有class")
    @GetMapping("/getClassName/{packageName}")
    public List<String> getClassName(@PathVariable String packageName) {
        List<String> classes = findClassesInPackage(packageName);
        List<String> classNameList = new ArrayList<>();
        for (String className : classes) {
            System.out.println(className);
            classNameList.add(className);
        }
        return classNameList;
    }

    @ApiOperation("获取加载器加载的所有class")
    @GetMapping("/getClass")
    public List<String> getClassName2() throws ClassNotFoundException {
        UsedClass usedClass = new UsedClass();

        List<String> classNameList = new ArrayList<>();

        // 获取当前线程的ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        Class aClass = Class.forName("com.liguo.demo.core.study.classloader.case1.NeedLoadClass1", true, new MyClassLoader());
        classLoader = aClass.getClassLoader();
        // 遍历ClassLoader层次结构
        while (classLoader != null) {
            // 获取ClassLoader加载的类
            Class[] classes = getLoadedClasses(classLoader);

            // 输出已加载的类的信息
            for (Class clazz : classes) {
                System.out.println(clazz.getName());
                classNameList.add(clazz.getName());
            }

            // 获取父ClassLoader
            classLoader = classLoader.getParent();
        }

        return classNameList;
    }

    @ApiOperation("加载磁盘class")
    @PostMapping("/loadClass")
    public void loadClass(@RequestParam("dir") String dir,
                                     @RequestParam("classPath") String classPath) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // 创建自定义类加载器，指定类文件所在目录
        MyClassLoader myClassLoader = new MyClassLoader();
        // MyClassLoader.classMap.put("com.liguo.demo.core.study.classloader.case1.NeedLoadClass1", "D:/class/NeedLoadClass1.class");
        MyClassLoader.classMap.put(classPath, dir);

        // 加载指定类（假设类名为 com.example.MyClass）
        Class<?> clazz = myClassLoader.loadClass(classPath);

        // 输出类加载器
        System.out.println("Class loaded by: " + clazz.getClassLoader());

        // 实例化类并调用方法（假设该类有一个无参构造函数）
        Object instance = clazz.getDeclaredConstructor().newInstance();
        instance.toString();
        System.out.println("Instance: " + instance);

        Method sayHello = instance.getClass().getMethod("sayHello");
        sayHello.invoke(instance);
    }

    private static Class[] getLoadedClasses(ClassLoader classLoader) {
        try {
            // 通过反射获取ClassLoader的加载的类的域
            Field classesField = ClassLoader.class.getDeclaredField("classes");
            classesField.setAccessible(true);

            // 获取ClassLoader加载的类
            Vector<Class> classes = (Vector<Class>) classesField.get(classLoader);

            return classes.toArray(new Class[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Class[0];
    }

    public static List<String> findClassesInPackage(String packageName) {
        List<String> classes = new ArrayList<>();
        String path = packageName.replace('.', '/');
        URL resource = ClassLoader.getSystemClassLoader().getResource(path);

        if (resource != null) {
            File directory = new File(resource.getFile());
            if (directory.exists()) {
                for (File file : directory.listFiles()) {
                    if (file.isFile() && file.getName().endsWith(".class")) {
                        classes.add(packageName + '.' + file.getName().replace(".class", ""));
                    }
                }
            }
        }
        return classes;
    }

}