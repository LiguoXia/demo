package com.liguo.demo.consume.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class Cron extends Model {
    private Long id;
    private String cronId;
    private String cron;
}
