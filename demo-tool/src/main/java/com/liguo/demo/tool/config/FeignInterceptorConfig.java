package com.liguo.demo.tool.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/1 12:17
 * @since 0.0.1
 */
public class FeignInterceptorConfig {
    protected List<String> requestHeaders = new ArrayList();

    public FeignInterceptorConfig() {
    }

    @PostConstruct
    public void initialize() {
        this.requestHeaders.add("ticket");
        this.requestHeaders.add("_TOKEN_KEY_");
        this.requestHeaders.add("__sf.beic.USER_NAME__");
        this.requestHeaders.add("__sf.beic.LESSEE_ID__");
        this.requestHeaders.add("__sf.beic.LESSEE_ACCOUNT_SOURCE__");
        this.requestHeaders.add("__sf.beic.LESSEE_DEPT_SOURCE__");
        this.requestHeaders.add("__sf.beic.LESSEE_EMP_SOURCE__");
    }

    @Bean
    public RequestInterceptor baseFeignInterceptor() {
        return (template) -> {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest request = attributes.getRequest();
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while(headerNames.hasMoreElements()) {
                        String headerName = (String)headerNames.nextElement();
                        if (this.requestHeaders.stream().anyMatch((e) -> {
                            return e.equalsIgnoreCase(headerName);
                        })) {
                            String headerValue = request.getHeader(headerName);
                            template.header(headerName, new String[]{headerValue});
                        }
                    }
                }
            }

        };
    }
}
