package com.liguo.demo.core.study.集合;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/15 16:01
 * @since 0.0.1
 */
@Slf4j
public class MapTest {
    public static void main(String[] args) {
        HashMap map = new HashMap(1000);
        for (int i = 0; i < 10000000; i++) {
            map.put(i, "a" + i);
        }
        log.info("111");
        int mapSizeInBytes = getMapSizeInBytes(map);
        System.out.println("Map size in bytes: " + mapSizeInBytes);
    }
    public static int getMapSizeInBytes(Map<?, ?> map) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(map);
            return baos.size();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
