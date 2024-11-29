package com.liguo.demo.gateway.route.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author joe
 * @since 2023-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value="TRouteRule对象", description="")
public class TRouteRule extends Model {

    private static final long serialVersionUID = 4538771776933923399L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "路由ID")
    private String routeId;

    @ApiModelProperty(value = "路由请求路径")
    private String path;

    @ApiModelProperty(value = "服务ID")
    private String serviceId;

    @ApiModelProperty(value = "服务URL")
    private String url;

    @ApiModelProperty(value = "是否去除前缀")
    private Boolean stripPrefix;

    @ApiModelProperty(value = "是否重试")
    private Boolean retryable;

    @ApiModelProperty(value = "敏感头部信息定义")
    private String sensitiveHeaders;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createdBy;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "更新人")
    private String modifiedBy;


}
