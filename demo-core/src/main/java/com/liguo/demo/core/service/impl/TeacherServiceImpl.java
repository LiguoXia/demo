package com.liguo.demo.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liguo.demo.core.dao.UserMapper;
import com.liguo.demo.core.pojo.entity.User;
import com.liguo.demo.core.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * 用户信息实现类
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/14
 * @since 0.0.1
 */
@Service("teacher")
public class TeacherServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public String task() {
        return "教书";
    }
}
