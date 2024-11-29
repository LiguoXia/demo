package com.liguo.demo.core.study.reference;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.liguo.demo.core.pojo.bo.BoDemo;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/9/19 14:27
 * @since 0.0.1
 */
@Slf4j
public class ListTest {
    public static void main(String[] args) {
        List<BoDemo> boDemos = new ArrayList<>();
        boDemos.add(new BoDemo().setA("a").setB(12).setC(DateUtil.offsetDay(new Date(), 1)));
        boDemos.add(new BoDemo().setA("a2").setB(12).setC(DateUtil.offsetDay(new Date(), 2)));
        boDemos.add(new BoDemo().setA("a3").setB(12).setC(DateUtil.offsetDay(new Date(), 3)));

        BoDemo boDemo = new BoDemo().setA("A").setB(12).setC(new Date());
        BoDemo boDemoTmp = new BoDemo();
        BeanUtil.copyProperties(boDemo, boDemoTmp);
        List<BoDemo> boDemos1 = new ArrayList<>();
        for (BoDemo boDemo1: boDemos) {
            boDemoTmp.setC(boDemo1.getC());
            boDemos1.add(boDemoTmp);
        }
        log.info(JSON.toJSONString(boDemos1));
    }
}
