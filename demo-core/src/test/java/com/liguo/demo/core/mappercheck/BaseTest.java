package com.liguo.demo.core.mappercheck;

import cn.hutool.core.util.StrUtil;
import com.liguo.demo.core.DemoCoreApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/23 16:00
 * @since 0.0.1
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {DemoCoreApplication.class})
@SpringBootTest
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class BaseTest {

    protected static void setObjectProperties(Object obj) throws IllegalAccessException, InvocationTargetException {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        log.info("fields size:{}", fields.length);
        for(Field field : fields) {
            Class<?> fieldType = field.getType();
            Method setFieldMethod = null;
            try {
                setFieldMethod = clazz.getDeclaredMethod(StrUtil.genSetter(field.getName()), fieldType);
            } catch (NoSuchMethodException e) {
                log.warn("方法:{}不存在!", StrUtil.genSetter(field.getName()));
            }
            if(setFieldMethod == null) {
                continue;
            }
            setFieldMethod.setAccessible(true);
            Object fieldValue = instantiate(fieldType);
            setFieldMethod.invoke(obj, fieldValue);
        }
    }

    /**
     * 实例化对象，如果对象是非基本类型，则会递归调用该方法
     * @param type
     * @return
     */
    protected static Object instantiate(Class<?> type) {
        // 基本类型
        if (type == byte.class) {
            return (byte) 1;
        }
        if (type == short.class) {
            return (short) 1;
        }
        if (type == int.class) {
            return (int) 1;
        }
        if (type == long.class) {
            return (long) 1;
        }
        if (type == float.class) {
            return (float) 1;
        }
        if (type == double.class) {
            return (double) 1;
        }
        if (type == char.class) {
            return (char) 1;
        }
        if (type == boolean.class) {
            return true;
        }
        // 基础类型的包装类
        if (type == Byte.class) {
            return Byte.valueOf((byte) 1);
        }
        if (type == Short.class) {
            return Short.valueOf((short) 1);
        }
        if (type == Integer.class) {
            return Integer.valueOf((int) 1);
        }
        if (type == Long.class) {
            return Long.valueOf((long) 1);
        }
        if (type == Float.class) {
            return Float.valueOf((float) 1);
        }
        if (type == Double.class) {
            return Double.valueOf((double) 1);
        }
        if (type == Character.class) {
            return Character.valueOf((char) 1);
        }
        if (type == Boolean.class) {
            return Boolean.valueOf(true);
        }
        // 字符串
        if (type == String.class) {
            return String.valueOf(1);
        }
        // 日期
        if (type == Date.class) {
            return new Date();
        }
        if (type == Timestamp.class) {
            return new Timestamp(System.currentTimeMillis());
        }
        // 数组
        if (type.isArray()) {
            Object array = Array.newInstance(type.getComponentType(), 1);
            Array.set(array, 0, instantiate(type.getComponentType()));
            return array;
        }
        // 枚举
        if (type.isEnum()) {
            return type.getEnumConstants()[0];
        }

        try {
            // 其他对象
            Object instance = type.newInstance();
            setObjectProperties(instance);
            return instance;
        } catch (Exception e) {
            log.warn("can not instantiate, type: " + type + ", e: " + e.getMessage());
            return null;
        }
    }
}
