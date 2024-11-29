package com.liguo.demo.core.pojo.bo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/9/19 14:27
 * @since 0.0.1
 */
@Data
@Accessors(chain = true)
public class BoDemo implements Serializable {
    private String a;
    private Integer b;
    @JSONField(format = "yyyy-MM-dd")
    private Date c;
}
