package com.liguo.demo.core.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 自定义业务异常
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/15 23:52
 * @since 0.0.1
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class BizException extends RuntimeException {
    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;
}
