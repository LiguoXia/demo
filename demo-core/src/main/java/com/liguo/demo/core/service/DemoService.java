package com.liguo.demo.core.service;


import com.liguo.demo.core.pojo.entity.Demo;
import org.apache.ibatis.executor.BaseExecutor;

import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/20 16:49
 * @since 0.0.1
 */
public interface DemoService {
    List<Demo> findAll();

    /**
     * 默认情况下会创建一个新的 SqlSession
     * <p>使用 Spring 的 @Transactional 注解,Spring 会管理 SqlSession 的生命周期，
     * <p>在一个事务中执行多次查询时，所有查询都将在同一个 SqlSession 中执行
     * @see BaseExecutor#query(org.apache.ibatis.mapping.MappedStatement, java.lang.Object, org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler, org.apache.ibatis.cache.CacheKey, org.apache.ibatis.mapping.BoundSql)
     *
     * @param id ID
     * @return Demo对象
     */
    Demo findById(int id);

    /**
     * 验证二级缓存
     *
     * @param id
     * @return
     */
    Demo findByIdCache2(int id);

    void save(Demo demo);

    void update(Demo demo);

    void deleteById(int id);
}
