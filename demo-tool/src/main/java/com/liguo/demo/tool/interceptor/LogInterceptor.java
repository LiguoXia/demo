package com.liguo.demo.tool.interceptor;

import cn.hutool.core.util.StrUtil;
import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/1 22:07
 * @since 0.0.1
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static final String TRACE_ID = "traceId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = request.getHeader(TRACE_ID);
        if (StrUtil.isEmpty(traceId)) {
            MDC.put("traceId", UUID.randomUUID().toString());
        } else {
            MDC.put(TRACE_ID, traceId);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //防止内存泄露
        MDC.remove("traceId");
    }
}
