package com.liguo.demo.consume.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liguo.demo.consume.pojo.entity.Cron;
import com.liguo.demo.consume.pojo.entity.CronQuery;
import com.liguo.demo.consume.pojo.vo.PageQueryReq;

/**
 * dsc
 * [mybatis-plus分页查询写法](https://blog.csdn.net/weixin_46146718/article/details/126962110)
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/2 10:21
 * @since 0.0.1
 */
public interface CronService extends IService<Cron> {

    /**
     * 分页查询
     */
    Page<Cron> selectByPage(PageQueryReq<Cron, CronQuery> req);

    /**
     * 通过ID获取
     *
     * @param id ID
     * @return Corn表达式
     */
    Cron getById1(Long id);
}
