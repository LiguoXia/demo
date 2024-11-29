package com.liguo.demo.core.study.反射与代理;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class 类确实是所有 .class 文件的抽象类，它在 Java 中表示一个类的运行时信息。每个 .class 文件对应于一个 Class 类的实例。
 * 这个实例包含了类的结构信息，包括类名、字段、方法、构造方法等。
 *
 * 每当一个类被加载到内存中时，都会生成一个对应的 Class 对象。这个过程通常是由类加载器完成的。
 * 而且，一个类在 JVM 中只会有一个对应的 Class 对象，无论这个类被加载了多少次。
 * 如果你多次加载同一个类，每次加载都会生成一个新的 Class 对象，但它们指向同一个运行时类定义。
 *
 * 可以通过 Class 对象来获取关于类的信息，创建类的实例，调用类的方法等。这使得在运行时可以动态地操作和获取类的信息，这也是 Java 反射机制的基础。
 * 反射机制允许在运行时检查类的信息、调用类的方法、获取和设置类的字段等。
 *
 * 因此，可以将 Class 类看作是 Java 中对类的元信息的抽象，通过它可以在运行时获取和操作类的信息。
 *
 * https://www.liaoxuefeng.com/wiki/1252599548343744/1264804244564000
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/14 15:07
 * @since 0.0.1
 */
@Slf4j
public class 代理测试类 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        reflection();

        /*printClassInfo("".getClass());
        printClassInfo(Runnable.class);
        printClassInfo(java.time.Month.class);
        printClassInfo(String[].class);
        printClassInfo(int.class);*/

        //printClassInfo(被代理类.class);
    }

    static void printClassInfo(Class cls) {

        log.info("\n-----------------------------------");
        System.out.println("Class name: " + cls.getName());
        System.out.println("Simple name: " + cls.getSimpleName());
        if (cls.getPackage() != null) {
            System.out.println("Package name: " + cls.getPackage().getName());
        }
        System.out.println("is interface: " + cls.isInterface());
        System.out.println("is enum: " + cls.isEnum());
        System.out.println("is array: " + cls.isArray());
        System.out.println("is primitive: " + cls.isPrimitive());
    }

    /**
     * Java的反射是指程序在运行期可以拿到一个对象的所有信息
     * <p>
     * 每一个.class JVM记载到内存都会创建这个Class文件的实例对象class
     * <p>
     * 由于JVM为每个加载的class创建了对应的Class实例，并在实例中保存了该class的所有信息，
     * 包括类名、包名、父类、实现的接口、所有方法、字段等，因此，如果获取了某个Class实例，
     * 我们就可以通过这个Class实例获取到该实例对应的class的所有信息。
     * <p>
     * 这种通过Class实例获取class信息的方法称为反射（Reflection）
     * 如何获取一个class的Class实例？有三个方法
     */
    public static void reflection() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {
        被代理类 d = new 被代理类();

        // 方法一：直接通过一个class的静态变量class获取：
        Class cls1 = 被代理类.class;

        // 方法二：如果我们有一个实例变量，可以通过该实例变量提供的getClass()方法获取：
        Class cls2 = d.getClass();

        // 方法三：如果知道一个class的完整类名，可以通过静态方法Class.forName()获取：
        Class<?> cls3 = Class.forName("com.liguo.demo.core.study.反射与代理.被代理类");

        // 因为Class实例在JVM中是唯一的，所以，上述方法获取的Class实例是同一个实例。可以用==比较两个Class实例：
        log.info("被代理类 class实例是否相等：{}", cls1 == cls2);
        log.info("被代理类 class实例是否相等：{}", cls2 == cls3);

        // 通过反射访问字段 NoSuchFieldException
        Field field = cls1.getField("str");
        log.info("被代理类 str属性:{}", field);

        Field field1 = cls1.getDeclaredField("str1");
        log.info("被代理类 私有str属性:{}", field1);

        // 获取属性值
        Object value = field.get(d);
        log.info("通过反射获取对象属性值:{}", value);

        // 获取私有属性值
        // 需要设置访问属性允许访问  IllegalAccessException with modifiers "private"
        field1.setAccessible(true);
        Object value1 = field1.get(d);
        log.info("通过反射获取对象私有属性值:{}", value1);

        // 设置值
        // 设置字段值是通过Field.set(Object, Object)实现的，其中第一个Object参数是指定的实例，第二个Object参数是待修改的值
        log.info("设置值之前对象:{}", d.toString());
        field.set(d, "3");
        field1.set(d, "4");
        log.info("设置值之后对象:{}", d.toString());

        // 获取方法 参数为 实例方法的参数类型列表 NoSuchMethodException
        Method method = cls3.getMethod("say", String.class);
        log.info("通过反射获取对象实例方法:{}", method);

        // 获取私有方法
        Method method1 = cls3.getDeclaredMethod("say1", String.class);
        log.info("通过反射获取对象实例私有方法:{}", method1);
        /**
         *  {@link java.lang.reflect.Modifier.PRIVATE}
         */
        log.info("方法名称:{},方法返回值类型:{},方法的参数类型:{},方法的修饰符:{}", method1.getName(), method1.getReturnType(), method1.getParameterTypes(), method1.getModifiers());

        // 调用类的方法 InvocationTargetException setAccessible(true)可能会失败。如果JVM运行期存在SecurityManager
        method1.setAccessible(true);
        String r = (String) method1.invoke(d, "123");
        log.info("通过反射调用类的实例的私有方法, 返回:{}", r);

        // 调用构造方法获取实例
        // Class.newInstance()的局限是，它只能调用该类的public无参数构造方法。如果构造方法带有参数，或者不是public，就无法直接通过Class.newInstance()来调用。
        // 为了调用任意的构造方法，Java的反射API提供了Constructor对象，它包含一个构造方法的所有信息，可以创建一个实例。
        // Constructor对象和Method非常类似，不同之处仅在于它是一个构造方法，并且，调用结果总是返回实例：

        // 获取构造方法 getDeclaredConstructor可以获取私有构造方法
        Constructor cons1 = 被代理类.class.getConstructor(String.class, String.class);
        // 调用构造方法:
        被代理类 con = (被代理类) cons1.newInstance("456", "789");
        log.info("通过构造方法获取实例:{}", con);

        // 获取父类的class
        Class i = Integer.class;
        Class n = i.getSuperclass();
        log.info("Integer父类class实例{}:", n);
        Class o = n.getSuperclass();
        log.info("Integer父类的父类class实例:{}", o);
        log.info("Integer父类的父类的父类class实例:{}", o.getSuperclass());

        // 获取接口
        Class s = Integer.class;
        Class[] is = s.getInterfaces();
        for (Class ii : is) {
            log.info("Integer的接口:{}", ii);
        }

        被代理类 d1 = (被代理类) cls3.newInstance();
        d1.say("liGuo");
    }
}
