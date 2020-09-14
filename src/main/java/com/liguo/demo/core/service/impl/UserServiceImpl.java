package com.liguo.demo.core.service.impl;

import com.liguo.demo.core.entity.User;
import com.liguo.demo.core.mapper.UserMapper;
import com.liguo.demo.core.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
