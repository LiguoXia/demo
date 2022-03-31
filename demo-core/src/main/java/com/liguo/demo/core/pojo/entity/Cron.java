package com.liguo.demo.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/25 19:14
 * @since 0.0.1
 */
@Data
@TableName("cron")
@EqualsAndHashCode(callSuper = false)
public class Cron {
    private String cronId;
    private String cron;
}
