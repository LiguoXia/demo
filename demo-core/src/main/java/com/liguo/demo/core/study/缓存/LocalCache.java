package com.liguo.demo.core.study.缓存;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/16 12:59
 * @since 0.0.1
 */
@Component
public class LocalCache {

    Map<String, Object> concurrentLocalCache = new ConcurrentHashMap<>();

    Cache<String, Object> guavaCache = CacheBuilder.newBuilder()
            .expireAfterWrite(10, TimeUnit.MINUTES) // 设置缓存项的过期时间
            .maximumSize(1000) // 设置缓存的最大大小
            .build();

    private static volatile LocalCache instance;

    private Map<String, Object> cacheMap;

    private LocalCache() {
        // 初始化为ConcurrentHashMap
        cacheMap = new ConcurrentHashMap<>();

        // 从数据库加载数据到缓存
        loadFromDatabase();
    }

    // 从数据库加载数据到缓存的逻辑
    private void loadFromDatabase() {
        // 这里可以执行数据库查询操作，并将查询结果放入缓存
        // 示例：从数据库查询用户数据，将结果放入缓存
        // List<User> users = userDao.getAllUsers();
        // for (User user : users) {
        //     cacheMap.put(user.getId(), user);
        // }
    }

    public static LocalCache getInstance() {
        if (instance == null) {
            synchronized (LocalCache.class) {
                if (instance == null) {
                    instance = new LocalCache();
                }
            }
        }
        return instance;
    }

    public void put(String key, Object value) {
        cacheMap.put(key, value);
    }

    public Object get(String key) {
        return cacheMap.get(key);
    }

    public void clear() {
        cacheMap.clear();
    }


}
