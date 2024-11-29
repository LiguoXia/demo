package com.liguo.demo.consume.dao;

import com.liguo.demo.consume.pojo.entity.Demo;
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
@Mapper
public interface DemoMapper {
    List<Demo> selectAll();

    Demo selectById(int id);

    int insert(Demo demo);

    int update(Demo demo);

    int deleteById(int id);
}
