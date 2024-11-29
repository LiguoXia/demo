package com.liguo.demo.consume.service.impl;

import com.liguo.demo.consume.dao.DemoMapper;
import com.liguo.demo.consume.pojo.entity.Demo;
import com.liguo.demo.consume.service.DemoService;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public List<Demo> findAll() {
        return demoMapper.selectAll();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Demo findById(int id) {
        Demo demo = demoMapper.selectById(id);
        Demo demo1 = demoMapper.selectById(id);
        log.info("是否走缓存:{}", demo == demo1);
        return demo;
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
