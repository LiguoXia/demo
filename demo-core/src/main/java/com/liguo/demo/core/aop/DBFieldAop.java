package com.liguo.demo.core.aop;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

/**
 * 数据库字段切面逻辑 TODO 未生效 待修复
 * <p>相同字段统一赋值</p>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/15 23:51
 * @since 0.0.1
 */

@Aspect
@Slf4j
public class DBFieldAop {

    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";
    private static final String CREATED_BY = "createdBy";
    private static final String MODIFIED_BY = "modifiedBy";
    private static final String SYSTEM_USER = "System";

    private static final String INSERT_METHOD_PREFIX = "insert";
    private static final String SAVE_METHOD_PREFIX = "save";
    private static final String UPDATE_METHOD_PREFIX = "update";

    @Pointcut("execution(public * com.liguo.demo..*.dao.*.*(..))")
    public void operationPointCut() {
    }

    @Before("operationPointCut()")
    public void before(JoinPoint point) {
        try {
            MethodSignature signature = (MethodSignature) point.getSignature();
            Method method = signature.getMethod();
            String methodName = method.getName();
            if (methodName.startsWith(INSERT_METHOD_PREFIX) || methodName.startsWith(SAVE_METHOD_PREFIX)) {
                beforeInsert(point);
            } else if (methodName.startsWith(UPDATE_METHOD_PREFIX)) {
                beforeUpdate(point);
            }
        } catch (Exception e) {
            log.error("Set create or update info error.", e);
        }
    }


    private void beforeInsert(JoinPoint point) {
        Object[] args = point.getArgs();
        if (args != null && args.length > 0) {
            Object argument = args[0];
            BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
            //设置创建时间和修改时间
            if (beanWrapper.isWritableProperty(CREATE_TIME)) {
                beanWrapper.setPropertyValue(CREATE_TIME, new Date());
            }
            if (beanWrapper.isWritableProperty(UPDATE_TIME)) {
                beanWrapper.setPropertyValue(UPDATE_TIME, new Date());
            }
            //设置创建人和修改人
            if (beanWrapper.isWritableProperty(CREATED_BY)) {
                // beanWrapper.setPropertyValue(CREATED_BY, this.getCurrentUserName());
            }
            if (beanWrapper.isWritableProperty(MODIFIED_BY)) {
                // beanWrapper.setPropertyValue(MODIFIED_BY, this.getCurrentUserName());
            }
            log.info("After insert = {}", ToStringBuilder.reflectionToString(argument, ToStringStyle.SHORT_PREFIX_STYLE));
        }
    }

    private void beforeUpdate(JoinPoint point) {
        Object[] args = point.getArgs();
        if (args != null && args.length > 0) {
            Object argument = args[0];
            //如果参数是map类型
            if (argument instanceof Map) {
                Map map = (Map) argument;
                map.put(UPDATE_TIME, new Date());
                // map.put(MODIFIED_BY, this.getCurrentUserName());
            } else {
                BeanWrapper beanWrapper = new BeanWrapperImpl(argument);
                //设置修改时间
                if (beanWrapper.isWritableProperty(UPDATE_TIME)) {
                    beanWrapper.setPropertyValue(UPDATE_TIME, new Date());
                }
                //设置修改人
                if (beanWrapper.isWritableProperty(MODIFIED_BY)) {
                    // beanWrapper.setPropertyValue(MODIFIED_BY, this.getCurrentUserName());
                }
            }
            log.info("After update = {}", ToStringBuilder.reflectionToString(argument, ToStringStyle.SHORT_PREFIX_STYLE));
        }
    }
}
