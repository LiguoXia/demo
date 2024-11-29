package com.liguo.demo.core.study.tree.calcscore;

import lombok.Data;

import java.math.BigDecimal;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/19 12:17
 * @since 0.0.1
 */
@Data
public class Model {
    private Long id;
    private Long parentId;
    private int level;
    private String nodeCode;
    private BigDecimal weight;
    private BigDecimal score;
    private String parentCode;
}
