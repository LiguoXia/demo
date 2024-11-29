package com.liguo.demo.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 这里是写法1
 *
 *
 * 写法2
 * @Configuration
 * public class WebConfig {
 *
 *     @Bean
 *     public FilterRegistrationBean filterRegistration1() {
 *         FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
 *         filterRegistrationBean.setFilter(new MyFilter3());
 *         filterRegistrationBean.addUrlPatterns("/*");//定义过滤器对哪些请求路径进行过滤，/*表示对所有请求都过滤
 *         filterRegistrationBean.setOrder(2);//定义过滤器的执行优先级，数据越小优先级越高
 *         return filterRegistrationBean;
 *     }
 *
 *     @Bean
 *     public FilterRegistrationBean filterRegistration2() {
 *         FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
 *         filterRegistrationBean.setFilter(new MyFilter4());
 *         filterRegistrationBean.addUrlPatterns("/*");
 *         filterRegistrationBean.setOrder(1);
 *         return filterRegistrationBean;
 *     }
 * }
 *
 * 然后实现即可，不用什么注解
 * public class MyFilter3 implements Filter {
 *
 * }
 *
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/4 22:30
 * @since 0.0.1
 */
@Slf4j
@Component
@WebFilter(urlPatterns = "/*")  // 拦截所有请求
public class LoggingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 初始化逻辑（可选）
        log.info("filterConfig:{}", filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        long startTime = System.currentTimeMillis();

        // 记录请求的URI
        System.out.println("LoggingFilter---Request URI: " + httpRequest.getRequestURI());

        // 继续执行过滤链
        chain.doFilter(request, response);

        long endTime = System.currentTimeMillis();
        System.out.println("LoggingFilter---Request Processing Time: " + (endTime - startTime) + " ms");
    }

    @Override
    public void destroy() {
        // 销毁逻辑（可选）
        log.info("LoggingFilter---销毁");
    }
}
