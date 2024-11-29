package com.liguo.demo.core.config.webmvcconfig;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/30 12:06
 * @since 0.0.1
 */
@Data
public class SysUser implements Serializable {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("员工姓名")
    private String empName;
}
