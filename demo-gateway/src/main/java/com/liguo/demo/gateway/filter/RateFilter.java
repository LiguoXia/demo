package com.liguo.demo.gateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 限流过滤器
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/11 10:59
 * @since 0.0.1
 */
@Component
@Slf4j
public class RateFilter extends ZuulFilter {

    private static int count = 0;

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(5);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        /**
         *拿不到令牌马上返回。尝试获取桶里的令牌，如果有，则返回true
         *并且，总的令牌数减1。没有则返回false。
         */
        //requestContext.setSendZuulResponse(false);
        if (!RATE_LIMITER.tryAcquire()) {
            System.out.println("ratefilter拿不到令牌，被限流了" + count++);
            // 不往后继续执行过滤器
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.TOO_MANY_REQUESTS.value());
        }
        return null;
    }
}