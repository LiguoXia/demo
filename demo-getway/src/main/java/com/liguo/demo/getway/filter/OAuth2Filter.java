package com.liguo.demo.getway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

/**
 * OAuth2 认证过滤器
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/14
 * @since 0.0.1
 */
public class OAuth2Filter extends ZuulFilter {
    @Override
    public String filterType() {
        return null;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
