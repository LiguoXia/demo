package com.liguo.demo.consume.service;

import com.liguo.demo.consume.pojo.entity.Demo;

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

    Demo findById(int id);

    void save(Demo demo);

    void update(Demo demo);

    void deleteById(int id);
}
