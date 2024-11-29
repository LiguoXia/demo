package com.liguo.demo.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liguo.demo.core.pojo.entity.DyrPorder;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author liguo
 * @since 2023-02-27
 */
public interface IDyrPorderService extends IService<DyrPorder> {
    List<DyrPorder> getAll()  throws UnsupportedEncodingException;
}
