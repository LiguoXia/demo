package com.liguo.demo.tool.pojo.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;

/**
 * 分页查询对象
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/4 19:49
 * @since 0.0.1
 */
@ApiModel(description = "分页查询对象")
@Data
public class PageQueryReq<E, C> {
    @ApiModelProperty("每页显示的数量")
    private Integer size = 10;
    @ApiModelProperty("当前页数")
    private Integer current = 1;
    @ApiModelProperty("查询条件")
    @Valid
    private C condition;

    public Page<E> newPage() {
        return new Page((long) this.current, (long) this.size);
    }

    public PageQueryReq() {
    }
}
