package com.liguo.demo.core.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 全局缓存对象
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/22 16:41
 * @since 0.0.1
 */
@Slf4j
public class GlobalCache {

    /**
     * 实例对象
     */
    private static GlobalCache instance = new GlobalCache();

    /**
     * 全局对象
     */
    private static ConcurrentHashMap cache = new ConcurrentHashMap();

    /**
     * 私有构造方法
     */
    private GlobalCache() {
        //init();
    }

    /**
     * 获取单例实例
     */
    public static GlobalCache getInstance() {
        return instance;
    }

    /**
     * 获取所有缓存
     */
    public ConcurrentHashMap getHashMap() {
        return cache;
    }


    /**
     * 初始化
     */
    private void init() {

    }

    /**
     * 获取缓存
     *
     * @param key key值
     * @return 缓存对象
     */
    public Object getCache(Object key) {
        return cache.get(key);
    }

    /**
     * 设置缓存
     *
     * @param key   key值
     * @param value value值
     */
    public void setCache(Object key, Object value) {
        if (key == null) {
            log.warn("ConcurrentHashMap key cannot be null!");
            return;
        }
        if (value == null) {
            log.warn("ConcurrentHashMap value cannot be null!");
            return;
        }
        cache.put(key, value);
    }

    /**
     * 清除缓存
     */
    public void clearCache() {
        cache = new ConcurrentHashMap();
    }
}
