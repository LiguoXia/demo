package com.liguo.demo.core.service.impl;


import com.liguo.demo.core.dao.DemoMapper;
import com.liguo.demo.core.pojo.entity.Demo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class Tx2DemoServiceImpl {
    @Autowired
    private DemoMapper demoMapper;

    public Tx2DemoServiceImpl() {
        log.info("加载了Tx2DemoServiceImpl 111111111111111111111111111111111111111111111111111111");
    }

    @Transactional
    public void update(Demo demo) {
        demoMapper.update(demo);
    }

}
