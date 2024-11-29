package com.liguo.demo.core.dao;

import com.liguo.demo.core.pojo.entity.Demo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/20 16:47
 * @since 0.0.1
 */
// @CacheNamespace 和在配置文件中配置效果一样
@Mapper
public interface DemoMapper {
    List<Demo> selectAll();

    Demo selectById(int id);

    int insert(Demo demo);

    int update(Demo demo);

    int deleteById(int id);
}
