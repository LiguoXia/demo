package com.liguo.demo.tool.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * redis用户对象
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/17 20:58
 * @since 0.0.1
 */
@ApiModel(description = "redis用户对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo {
    private String id;
    private String name;
    private String pwd;
}
