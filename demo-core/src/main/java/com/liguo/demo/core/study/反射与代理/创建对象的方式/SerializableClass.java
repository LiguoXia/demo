package com.liguo.demo.core.study.反射与代理.创建对象的方式;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/17 16:18
 * @since 0.0.1
 */
@Slf4j
public class SerializableClass implements Serializable {

    // 1、版本控制： 防止由于类的变化导致反序列化时出现问题。如果类的结构发生变化，但没有更新 serialVersionUID，反序列化时会导致版本不匹配，可能导致意外的结果。
    // 2、兼容性： 提供了一种在类发生变化时保持向后兼容性的机制。通过显式声明 serialVersionUID，可以控制序列化版本，避免不同版本的类在序列化和反序列化过程中引发问题。
    private static final long serialVersionUID = 1L;

    // 使用 transient 关键字标记不希望被序列化的成员。
    public transient String transientStr = "transientStr";
    // public String transientStr = "transientStr";

    private String data;

    public SerializableClass(String data) {
        this.data = data;
    }

    public void display() {
        System.out.println("Data: " + data);
    }

    public static void main(String[] args) {
        // 序列化对象
        SerializableClass objToSerialize = new SerializableClass("Serialized Data");
        // E:\workspaces\demo\serializedObject.ser
        // 将对象转换为字节流进行持久化
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("serializedObject.ser"))) {
            oos.writeObject(objToSerialize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 反序列化对象
        // 字节流还原为对象
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("serializedObject.ser"))) {
            SerializableClass deserializedObj = (SerializableClass) ois.readObject();
            deserializedObj.display();
            log.info("transientStr:{}", deserializedObj.transientStr);
            log.info("classLoader:{}", deserializedObj.getClass().getClassLoader());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
