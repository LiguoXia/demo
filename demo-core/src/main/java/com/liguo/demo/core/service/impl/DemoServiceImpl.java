package com.liguo.demo.core.service.impl;


import com.alicp.jetcache.anno.Cached;
import com.liguo.demo.core.dao.DemoMapper;
import com.liguo.demo.core.pojo.entity.Demo;
import com.liguo.demo.core.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.BaseExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/20 16:50
 * @since 0.0.1
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private DemoMapper demoMapper;

    public DemoServiceImpl () {
        log.info("加载了DemoServiceImpl 111111111111111111111111111111111111111111111111111111");
    }

    @Override
    public List<Demo> findAll() {
        return demoMapper.selectAll();
    }

    /**
     * mybatis缓存
     * <p>默认情况下会创建一个新的 SqlSession
     * <p>使用 Spring 的 @Transactional 注解,Spring 会管理 SqlSession 的生命周期，
     * <p>在一个事务中执行多次查询时，所有查询都将在同一个 SqlSession 中执行
     * @see BaseExecutor#query(org.apache.ibatis.mapping.MappedStatement, java.lang.Object, org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler, org.apache.ibatis.cache.CacheKey, org.apache.ibatis.mapping.BoundSql)
     *
     * @param id ID
     * @return Demo对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Demo findById(int id) {
        Demo demo = demoMapper.selectById(id);
        // 这里会使用一级缓存
        Demo demo1 = demoMapper.selectById(id);
        log.info("同一个事务（sqlSession）下,两次执行相同的sql,是否走了一级缓存:{}", demo == demo1);
        return demo;
    }

    /**
     * 验证一级缓存
     *
     * @param id
     * @return
     */
    @Override
    @Cached(name = "demo_", key = "#id", expire = 12000)
    public Demo findByIdCache2(int id) {
        log.info("验证是否走了二级缓存");
        return demoMapper.selectById(id);
    }

    @Override
    public void save(Demo demo) {
        demoMapper.insert(demo);
    }

    @Override
    public void update(Demo demo) {
        demoMapper.update(demo);
    }

    @Override
    public void deleteById(int id) {
        demoMapper.deleteById(id);
    }
}
