package com.liguo.demo.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 实体模板
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/23 22:05
 * @since 0.0.1
 */
@Data
public class EntityTemplate {
    // ID
    // `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 状态
    // `deduce_status` tinyint(3) unsigned NOT NULL DEFAULT 0 COMMENT '推导状态,0-初始化,1-运行中,2-成功,3-失败',
    private Integer status;

    // 状态
    // `deduce_status` varchar2(N) NOT NULL DEFAULT '00' COMMENT '推导状态,00-初始化,01-运行中,02-成功,03-失败',
    private String status1;

    // 日期
    // `org_voucher_accting_date` date DEFAULT NULL COMMENT '源凭证会计日期',
    @JsonFormat(pattern = "yyyy-MM-dd")
    private static Date date;

    // 日期
    @JsonFormat(pattern = "yyyyMMdd")
    private static Date date1;

    // 日期
    // `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 yyyy-MM-dd hh:mm:ss',
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    // 操作
    // `deduce_status` tinyint(3) unsigned NOT NULL DEFAULT 0 COMMENT '推导状态,0-初始化,1-运行中,2-成功,3-失败',
    private Integer operate;

    // 金额
    // `standard_amount_cent` bigint(20) DEFAULT NULL COMMENT '本位币金额',
    private Long amount;

    // 删除标识
    // `del_flag` bigint(20) unsigned NOT NULL DEFAULT 0 COMMENT '删除标识 0:未删除,删除赋值为id',
    private Long delFlag;
}
