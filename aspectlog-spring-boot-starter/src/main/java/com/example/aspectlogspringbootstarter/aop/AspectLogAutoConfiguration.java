package com.example.aspectlogspringbootstarter.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.PriorityOrdered;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/25 22:07
 * @since 0.0.1
 */
@Aspect
@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
@Configuration
// 当配置文件有aspectLog.enable=true时开启，如果配置文件没有设置aspectLog.enable也开启
@ConditionalOnProperty(prefix = "aspect.log", name = "enable", havingValue = "true", matchIfMissing = true)
public class AspectLogAutoConfiguration implements PriorityOrdered {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Around("@annotation(com.example.aspectlogspringbootstarter.aop.AspectLog) ")
    public Object isOpen(ProceedingJoinPoint thisJoinPoint)
            throws Throwable {
        //执行方法名称
        String taskName = thisJoinPoint.getSignature()
                .toString().substring(
                        thisJoinPoint.getSignature()
                                .toString().indexOf(" "),
                        thisJoinPoint.getSignature().toString().indexOf("("));
        taskName = taskName.trim();
        long time = System.currentTimeMillis();
        Object result = thisJoinPoint.proceed();
        logger.info("method:{} run :{} ms", taskName,
                (System.currentTimeMillis() - time));
        return result;
    }

    @Override
    public int getOrder() {
        //保证事务等切面先执行
        return Integer.MAX_VALUE;
    }
}
