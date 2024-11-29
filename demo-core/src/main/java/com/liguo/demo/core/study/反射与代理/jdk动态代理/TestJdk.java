package com.liguo.demo.core.study.反射与代理.jdk动态代理;

import lombok.extern.slf4j.Slf4j;
import sun.misc.ProxyGenerator;

import java.lang.reflect.Proxy;
import java.util.ArrayList;

/**
 * JDK 动态代理要求被代理的类必须实现一个或多个接口。通过 java.lang.reflect.Proxy 类创建代理。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 21:51
 * @since 0.0.1
 */
@Slf4j
public class TestJdk {
    public static void main(String[] args) {
        // 在Java 8及以下版本，可以在idea启动时这样设置属性：
        // -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true 或下面这样
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");

        // Java 9及之后
        // -Djdk.proxy.ProxyGenerator.saveGeneratedFiles=true
        // 添加了这个系统属性后，当创建动态代理实例的时候，JVM会将生成的代理类以 .class 文件形式保存在工程的根目录或者 com/sun/proxy 目录下。

        // 如何确定版本？
        // 如果还有人不知道如何确定是sun.misc.ProxyGenerator.saveGeneratedFiles还是jdk.proxy.ProxyGenerator.saveGeneratedFiles,
        // 可以打开sun.misc.ProxyGenerator类查看，注意这个变量saveGeneratedFiles

        // 为什么这样设置就可以生成代理类字节码到本地？
        // 还是在sun.misc.ProxyGenerator这里，搜索查看sun.misc.ProxyGenerator#generateProxyClass(java.lang.String, java.lang.Class<?>[], int)这个方法，
        // 可以发现上面设置完了后，我们的saveGeneratedFiles是true,此时就会生成对应的字节码到本地图中那个路径com/sun/poxy
        test2();
    }

    public static void test1() {
        UserServiceImpl impl = new UserServiceImpl();
        UserProxy userProxy = new UserProxy(impl);
        UserService userService = (UserService) Proxy.newProxyInstance(impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), userProxy);
        ClassLoader classLoader = impl.getClass().getClassLoader();
        log.info("classLoader:{}", classLoader.getClass().getSimpleName());
        log.info("classLoader:{}", classLoader.getParent().getClass().getSimpleName());
        //log.info("classLoader:{}", classLoader.getParent().getParent().getClass().getSimpleName());
        userService.addUser();
        userService.updateUser("：我是皮皮虾");
    }

    /**
     * @see Proxy.InvocationHandler
     * @see ProxyGenerator 生成字节码。
     *
     * 最终会生成一个代理类 UserService userService
     * public final class $Proxy0 extends Proxy implements UserService
     * 其中h就是 代理类的逻辑 new UserProxy(impl)
     *
     * 生成的代理类（InvocationHandler属性，代理类逻辑） -> 调用被代理类的方法 -> 实际是 代理类的invoke 方法
     */
    public static void test2() {
        UserService impl = new UserServiceImpl();
        // Proxy.newProxyInstance的时候生成了代理类的字节码并加载代理类
        // 默认是在内存中动态生成并直接加载的
        UserService userService = (UserService) Proxy.newProxyInstance(impl.getClass().getClassLoader(), impl.getClass().getInterfaces(), new UserProxy(impl));
        userService.addUser();
        userService.updateUser("：我是皮皮虾");

        ArrayList<String> a = new ArrayList<>();

    }
}
