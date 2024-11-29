package com.liguo.demo.core.study.反射与代理.反射;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * <pre>
 * tag：
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/28 15:59
 * @since 0.0.1
 */
public class ReflectionTest {
    public static void main(String[] args) throws Exception {
        // 获取类对象
        Class<?> clazz = ReflectionTarget.class;
        Class<?> clazzByAllName =  Class.forName("com.liguo.demo.core.study.反射与代理.反射.ReflectionTarget");
        ReflectionTarget target = new ReflectionTarget();
        Class<?> clazzByInstance = target.getClass();

        // 打印类名
        System.out.println("Class Name: " + clazz.getName()); // com.liguo.demo.core.study.反射与代理.反射.ReflectionTarget
        System.out.println("Class Name: " + clazz.getSimpleName()); // ReflectionTarget

        // 获取父类
        System.out.println(clazz.getSuperclass());  // class com.liguo.demo.core.study.反射与代理.反射.FatherCls

        // 获取实现的接口
        Class<?>[] interfaces = clazz.getInterfaces();  // interface com.liguo.demo.core.study.反射与代理.反射.InterfaceA interface com.liguo.demo.core.study.反射与代理.反射.InterfaceB
        for (Class<?> inter : interfaces) {
            System.out.println(inter.getName());    //
        }




        // 获取所有构造函数（包括私有）
        // - private com.liguo.demo.core.study.反射与代理.反射.ReflectionTarget(java.lang.String,java.lang.String)
        // - private com.liguo.demo.core.study.反射与代理.反射.ReflectionTarget(java.lang.String)
        // - public com.liguo.demo.core.study.反射与代理.反射.ReflectionTarget(java.lang.String,int)
        // - public com.liguo.demo.core.study.反射与代理.反射.ReflectionTarget()
        System.out.println("\nConstructors:");
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(" - " + constructor);
        }

        // 创建对象（使用无参公共构造函数）
        ReflectionTarget instance1 = (ReflectionTarget) clazz.getConstructor().newInstance();
        System.out.println("\nCreated instance using public no-arg constructor: " + instance1.publicField);

        // 创建对象（使用带参公共构造函数）
        Constructor<?> publicConstructor = clazz.getConstructor(String.class, int.class);
        ReflectionTarget instance2 = (ReflectionTarget) publicConstructor.newInstance("Public Field", 42);
        System.out.println("Created instance using public constructor: " + instance2.publicField + ", " + instance2.getPrivateField());

        // 创建对象（使用私有构造函数）
        Constructor<?> privateConstructor0 = clazz.getDeclaredConstructor();
        privateConstructor0.setAccessible(true); // 允许访问私有构造函数
        ReflectionTarget instance3 = (ReflectionTarget) privateConstructor0.newInstance();
        System.out.println("Created instance using private constructor: " + instance3.publicField);


        // 创建对象（使用私有构造函数）
        Constructor<?> privateConstructor = clazz.getDeclaredConstructor(String.class);
        privateConstructor.setAccessible(true); // 允许访问私有构造函数
        ReflectionTarget instance4 = (ReflectionTarget) privateConstructor.newInstance("Private Param");
        System.out.println("Created instance using private constructor: " + instance4.publicField);





        // 获取所有字段（包括私有）
        System.out.println("\nFields:");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(" - " + field);
        }

        // 修改私有字段值
        Field privateField = clazz.getDeclaredField("privateField");
        privateField.setAccessible(true);
        privateField.set(instance2, 99);
        System.out.println("Modified private field value: " + instance2.getPrivateField());






        // 获取所有方法（包括私有）
        System.out.println("\nMethods:");
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(" - " + method);
        }

        // 调用公共方法
        Method publicMethod = clazz.getMethod("publicMethod", String.class);
        publicMethod.invoke(instance1, "Hello Public Method");

        // 调用私有方法
        Method privateMethod = clazz.getDeclaredMethod("privateMethod", String.class);
        privateMethod.setAccessible(true); // 允许访问私有方法
        privateMethod.invoke(instance1, "Hello Private Method");

        // 调用静态公共方法
        Method publicStaticMethod = clazz.getMethod("publicStaticMethod");
        publicStaticMethod.invoke(null);

        // 调用静态私有方法
        Method privateStaticMethod = clazz.getDeclaredMethod("privateStaticMethod");
        privateStaticMethod.setAccessible(true);
        privateStaticMethod.invoke(null);

        // 调用 final 公共方法
        Method publicFinalMethod = clazz.getMethod("publicFinalMethod");
        publicFinalMethod.invoke(instance1);

        // 调用 final 私有方法
        Method privateFinalMethod = clazz.getDeclaredMethod("privateFinalMethod");
        privateFinalMethod.setAccessible(true);
        privateFinalMethod.invoke(instance1);


    }
}
