package com.liguo.demo.consume.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-09-14
 */
@Data
@TableName("t_order")
@EqualsAndHashCode(callSuper = false)
public class Order extends Model implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long orderId;

    private Integer userId;

    private String productName;

    private Integer count;
}
