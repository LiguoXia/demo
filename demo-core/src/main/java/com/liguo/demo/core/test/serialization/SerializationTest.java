package com.liguo.demo.core.test.serialization;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/22 22:58
 * @since 0.0.1
 */
@Slf4j
public class SerializationTest {

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setUserName("test");
        user.setPassword("test");
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(os);
            out.writeObject(user);
            out.flush();
            out.close();
            byte[] testByte = os.toByteArray();
            User user1 = (User) byteArrayToObject(testByte);
            os.close();
        }

        long endTime = System.currentTimeMillis();
        System.out.print("ObjectOutputStream 序列化时间：" + (endTime - startTime) + "\n");


        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
            byte[] userName = user.getUserName().getBytes();
            byte[] password = user.getPassword().getBytes();
            byteBuffer.putInt(userName.length);
            byteBuffer.put(userName);
            byteBuffer.putInt(password.length);
            byteBuffer.put(password);
            byteBuffer.flip();
            byte[] bytes = new byte[byteBuffer.remaining()];
        }
        long endTime1 = System.currentTimeMillis();
        System.out.print("ByteBuffer 序列化时间：" + (endTime1 - startTime1) + "\n");

    }

    /**
     * Byte数组转对象
     * @param bytes
     * @return
     */
    public static Object byteArrayToObject(byte[] bytes) {
        Object obj = null;
        ByteArrayInputStream byteArrayInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            obj = objectInputStream.readObject();
        } catch (Exception e) {
            log.error("byteArrayToObject failed, " + e);
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    log.error("close byteArrayInputStream failed, " + e);
                }
            }
            if (objectInputStream != null) {
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    log.error("close objectInputStream failed, " + e);
                }
            }
        }
        return obj;
    }
}

@Data
class User implements Serializable {
    private String userName;
    private String password;
}
