package com.liguo.demo.core.test.date;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.liguo.demo.core.util.IDateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

import static cn.hutool.core.date.DatePattern.PURE_DATETIME_FORMAT;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/23 21:32
 * @since 0.0.1
 */
@Slf4j
public class DateTest {

    public static void main(String[] args) {
        // 计算耗时,格式化输出
        long startTime = System.currentTimeMillis();

        // 获取当前时间字符串 2022-03-23 21:52:11
        String date = DateUtil.now();
        log.info("时间:{}", date);

        // 获取当前时间字符串 2022-03-23
        String today = DateUtil.today();
        log.info("日期:{}", today);

        // 获取日期年yyyy
        String year = DateUtil.format(DateUtil.date(), "yyyy");
        log.info("年份:{}", year);

        // 获取日期年yyyy
        int year1 = DateUtil.year(DateUtil.date());
        log.info("年份:{}", year1);

        // 获取月份
        int month = DateUtil.month(DateUtil.date());
        log.info("年份:{}", year1);

        // 当前时间：yyyyMMdd HH:mm:ss
        DateUtil.format(DateUtil.date(), "yyyy-MM-dd HH:mm:ss");
        // 当前时间前十分钟
        Date date2 = DateUtil.offsetMinute(DateUtil.date(), -10);
        log.info("当前时间前十分钟:{}", DateUtil.formatDateTime(date2));

        // 月初月末
        Date beginDate = DateUtil.beginOfMonth(DateUtil.date());
        Date endDate = DateUtil.endOfMonth(DateUtil.date());

        // yyyyMMddHHmmss
        String date3 = DateUtil.format(DateUtil.date(), PURE_DATETIME_FORMAT);
        log.info("时间yyyyMMddHHmmss:{}", date3);

        long dayDiff = DateUtil.between(beginDate, endDate, DateUnit.DAY, true);
        log.info("时间相差:{}天", dayDiff);


        String costTime = IDateUtil.costTime(startTime);
        log.info("耗时:{}", costTime);
    }

}
