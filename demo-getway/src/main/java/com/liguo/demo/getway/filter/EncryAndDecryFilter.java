package com.liguo.demo.getway.filter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 网关统一加解密过滤器
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/14
 * @since 0.0.1
 */
@Component
@Slf4j
public class EncryAndDecryFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String path = new UrlPathHelper().getPathWithinApplication(request);
        log.info("OpenApiResponseFilter request path is:{}", path);
        if (path.startsWith("/v1")) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() {

        RequestContext context = RequestContext.getCurrentContext();
        String body = null;
        try {
            // 获取返回值内容，加以处理
            InputStream stream = context.getResponseDataStream();
            body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));

            log.info("请求body:{}", body);

            HttpServletRequest request = context.getRequest();
            // 获取请求中的参数
            Map<String, Object> params = getParamMap(request);


        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        // 内容重新写入
        context.setResponseBody(body);
        return null;
    }

    public Map<String, Object> getParamMap(HttpServletRequest request) {

        String method = request.getMethod();
        log.info("{} >>> {}", request.getRequestURI(), method);

        LinkedHashMap paramMap = null;
        if ("GET".equals(method)) {
            Map<String, String[]> map = request.getParameterMap();
            if (CollectionUtils.isNotEmpty(map)) {
                paramMap = new LinkedHashMap();
                for (Map.Entry<String, String[]> entry : map.entrySet()) {
                    paramMap.put(entry.getKey(), entry.getValue()[0]);
                }
            }
        } else if ("POST".equals(method)) {
            try {
                InputStream inputStream = request.getInputStream();
                String body = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
                log.debug("原始参数：{}", body);
                paramMap = JSONObject.parseObject(body, LinkedHashMap.class, Feature.OrderedField);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        return paramMap;
    }
}
