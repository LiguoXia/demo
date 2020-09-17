package com.liguo.demo.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liguo.demo.core.pojo.dos.User;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */

public interface IUserService extends IService<User> {
    //返回用户的主要任务
    String task();
}
