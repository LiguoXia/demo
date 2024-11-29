package com.liguo.demo.tool.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/1 22:21
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class FeignInterceptor implements RequestInterceptor {
    private static final String TRACE_ID = "traceId";
    @Override
    public void apply(RequestTemplate template) {
        log.info("fegin拦截器设置TRACE_ID");
        template.header(TRACE_ID, (String) MDC.get(TRACE_ID));
    }
}
