package com.liguo.demo.consume.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liguo.demo.consume.dao.CronMapper;
import com.liguo.demo.consume.pojo.entity.Cron;
import com.liguo.demo.consume.pojo.entity.CronQuery;
import com.liguo.demo.consume.pojo.vo.PageQueryReq;
import com.liguo.demo.consume.service.CronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * dsc
 * [mybatis-plus分页查询写法](https://blog.csdn.net/weixin_46146718/article/details/126962110)
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/2 10:22
 * @since 0.0.1
 */
@Service
public class CronServiceImpl extends ServiceImpl<CronMapper, Cron> implements CronService {

    @Autowired
    private CronMapper cronMapper;


    /**
     *
     *
     * @param req
     * @return
     */
    @Override
    public Page<Cron> selectByPage(PageQueryReq<Cron, CronQuery> req) {
        Page<Cron> page = req.newPage();
        // 参数需要带上 Page
        List<Cron> list = cronMapper.selectByPage(page, req.getCondition());
        page.setRecords(list);
        return page;
    }

    /**
     * 通过ID获取
     *
     * @param id ID
     * @return Corn表达式
     */
    @Override
    public Cron getById1(Long id) {
        return cronMapper.selectById(id);
    }
}
