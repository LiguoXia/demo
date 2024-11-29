package com.liguo.demo.core.study.反射与代理.反射;

/**
 * <pre>
 * tag：
 * 需要被反射获取类信息的类
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/28 15:53
 * @since 0.0.1
 */
public class ReflectionTarget extends FatherCls implements InterfaceA, InterfaceB{
    // 共有成员变量
    public String publicField;

    // 私有成员变量
    private int privateField;

    // 无参共有构造函数
    public ReflectionTarget() {
        this.publicField = "Default Public Field";
        this.privateField = 0;
    }

    // 带参共有构造函数
    public ReflectionTarget(String publicField, int privateField) {
        this.publicField = publicField;
        this.privateField = privateField;
    }

    // 无参私有构造函数
    private ReflectionTarget(String privateParam) {
        this.publicField = "Private: " + privateParam;
    }

    // 带参私有构造函数
    private ReflectionTarget(String publicField, String privateField) {
        this.publicField = publicField;
        this.privateField = Integer.parseInt(privateField);
    }

    // 共有方法
    public void publicMethod(String input) {
        System.out.println("Public Method: " + input);
    }

    // 私有方法
    private void privateMethod(String input) {
        System.out.println("Private Method: " + input);
    }

    // 共有静态方法
    public static void publicStaticMethod() {
        System.out.println("Public Static Method");
    }

    // 私有静态方法
    private static void privateStaticMethod() {
        System.out.println("Private Static Method");
    }

    // 共有 final 方法
    public final void publicFinalMethod() {
        System.out.println("Public Final Method");
    }

    // 私有 final 方法
    private final void privateFinalMethod() {
        System.out.println("Private Final Method");
    }

    // Getter for privateField
    public int getPrivateField() {
        return privateField;
    }

    @Override
    public String faceA1(String paramA) {
        return "faceA1";
    }

    @Override
    public String faceB1(String paramB1) {
        return "faceB1";
    }
}
