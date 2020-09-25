package com.liguo.demo.core.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * ThreadLocal工具类,保存当前线程相关信息,用于线程间数据隔离
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/25 15:51
 * @since 0.0.1
 */
@Slf4j
public class ThreadLocalUtil {
    /**
     * 初始化方式1
     */
    private static ThreadLocal<List<Map<String, Object>>> THREAD_LOCAL_IST = ThreadLocal.withInitial(() -> Lists.newArrayList());

    /**
     * 初始化方式2
     */
    public static ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return "Test";
        }
    };

    /**
     * 返回当前线程List对象
     *
     * @return
     */
    public static List<Map<String, Object>> getList() {
        return THREAD_LOCAL_IST.get();
    }

    /**
     * 往当前线程List塞值
     *
     * @param list
     */
    public static void addList(List<Map<String, Object>> list) {
        THREAD_LOCAL_IST.get().addAll(list);
    }

    /**
     * 返回当前线程String对象
     *
     * @return
     */
    public static String getString() {
        return THREAD_LOCAL.get();
    }

    /**
     * 往当前线程List塞值
     *
     * @param str
     */
    public static void setString(String str) {
        THREAD_LOCAL.set(str);
    }

}
