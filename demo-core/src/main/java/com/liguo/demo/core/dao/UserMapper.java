package com.liguo.demo.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liguo.demo.core.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息数据库操作接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/14
 * @since 0.0.1
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
