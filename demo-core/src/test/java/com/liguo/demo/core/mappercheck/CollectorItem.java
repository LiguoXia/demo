package com.liguo.demo.core.mappercheck;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * sql执行明细
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/23 16:13
 * @since 0.0.1
 */
public class CollectorItem {
    // 执行时间
    private long elapse;
    // 是否成功
    private boolean success;
    // dao 接口名
    private String dao;
    // dao 方法名
    private String method;
    // 异常
    private Throwable error;

    public long getElapse() {
        return elapse;
    }

    public void setElapse(long elapse) {
        this.elapse = elapse;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getDao() {
        return dao;
    }

    public void setDao(String dao) {
        this.dao = dao;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
