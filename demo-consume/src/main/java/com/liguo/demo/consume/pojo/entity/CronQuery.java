package com.liguo.demo.consume.pojo.entity;

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
@EqualsAndHashCode(callSuper = false)
public class CronQuery {
    private Long id;
    private String cronId;
    private String cron;
}
