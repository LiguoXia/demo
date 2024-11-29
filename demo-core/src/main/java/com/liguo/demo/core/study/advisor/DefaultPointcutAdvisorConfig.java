package com.liguo.demo.core.study.advisor;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * <pre>
 * tag：
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/26 21:47
 * @since 0.0.1
 */
@Configuration
@EnableAspectJAutoProxy
public class DefaultPointcutAdvisorConfig {

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        // 定义 Pointcut，匹配 update1 方法
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("update1");

        // 定义 Advice，增强逻辑
        Advice advice = (MethodInterceptor) invocation -> {
            System.out.println("Before Method Execution");
            Object result = invocation.proceed();
            System.out.println("After Method Execution");
            return result;
        };

        // 创建 DefaultPointcutAdvisor
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    // @Bean
    public RegexpMethodPointcutAdvisor regexAdvisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setPattern(".*Service.*");
        advisor.setAdvice((MethodInterceptor) invocation -> {
            System.out.println("Regex Advisor: Intercepted method");
            return invocation.proceed();
        });
        return advisor;
    }
}
