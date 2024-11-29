package com.liguo.demo.core.study.反射与代理.cglib动态代理;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * <pre>
 * 1、目标类不能是 final 类： CGLIB 通过继承目标类来生成代理类，因此目标类不能声明为 final。
 * 2、代理的方法不能是 final 或 static： CGLIB 不能代理 final 或 static 方法，因为这些方法不能被重写。
 * 3、性能开销： CGLIB 代理相比 JDK 动态代理略有性能开销，但它不要求目标类实现接口。
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 22:53
 * @since 0.0.1
 */
public class CglibTest {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
        // 创建目标对象实例
        CglibUserServiceImpl real = new CglibUserServiceImpl();
        MethodInterceptor handler = new UserServiceCGlib(real);

       /* // 创建代理对象
        System.out.println("第一种方式。。。。。。");
        CglibUserServiceImpl proxy = (CglibUserServiceImpl) Enhancer.create(
                CglibUserServiceImpl.class,
                handler);

        // 调用代理对象的方法
        proxy.addUser();*/

        // 第二种创建方式
        System.out.println("第二种方式。。。。。。");
        UserServiceCGlib userServiceCGlib = new UserServiceCGlib(real);
        // public class CglibUserServiceImpl$$EnhancerByCGLIB$$13149ccf extends CglibUserServiceImpl implements Factory
        // 生成的代理类 继承了被代理类
        CglibUserServiceImpl proxy1 = (CglibUserServiceImpl) userServiceCGlib.getProxyInstance();
        User user = new User();
        user.setAge(27);
        user.setName("夏利国");
        proxy1.addUser(user, "xlg");
        // CglibUserServiceImpl proxy1 = (CglibUserServiceImpl) new UserServiceCGlib(real).getProxyInstance();
        // proxy1.addUser();
    }


    //  private MethodInterceptor CGLIB$CALLBACK_0;
    /*public final String addUser() {
        MethodInterceptor tmp4_1 = this.CGLIB$CALLBACK_0;
        if (tmp4_1 == null) {
            tmp4_1;
            CGLIB$BIND_CALLBACKS(this);
        }
        MethodInterceptor tmp17_14 = this.CGLIB$CALLBACK_0;
        if (tmp17_14 != null)
            return (String)tmp17_14.intercept(this, CGLIB$addUser$0$Method, CGLIB$emptyArgs, CGLIB$addUser$0$Proxy);
        return super.addUser();
    }*/
}
