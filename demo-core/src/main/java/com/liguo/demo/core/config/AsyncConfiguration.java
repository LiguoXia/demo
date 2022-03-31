package com.liguo.demo.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/26 21:34
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class AsyncConfiguration implements AsyncConfigurer {

    @Value("${demo.async.executor.corePoolSize:10}")
    private Integer corePoolSize;

    @Bean("asyncTaskExecutor")
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(20);
        executor.setKeepAliveSeconds(60);
        // 线程队列最大线程数,默认：80000
        executor.setQueueCapacity(80000);
        // 线程名称前缀
        executor.setThreadNamePrefix("Demo-Async-ThreadPool-");
        // 核心线程是否允许超时，默认:false
        executor.setAllowCoreThreadTimeOut(false);
        // IOC容器关闭时是否阻塞等待剩余的任务执行完成，默认:false（必须设置setAwaitTerminationSeconds）
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 阻塞IOC容器关闭的时间，默认：10秒（必须设置setWaitForTasksToCompleteOnShutdown）
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }

    @Override
    public Executor getAsyncExecutor() {
        return executor();
    }

    /**
     * 异步方法执行的过程中抛出的异常捕获
     *
     * @return
     */
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return ((ex, method, params) -> {
            log.info("参数:{},方法:{},异常信息:", params, method, ex);
        });
    }
}
