package com.liguo.demo.tool.config;

import com.liguo.demo.tool.interceptor.LogInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 实现 WebMvcConfigurer 允许你对 Spring MVC 进行灵活定制，常用于以下几类场景：
 * <p>
 * 配置跨域访问。
 * 添加或定制拦截器来处理请求。
 * 设置静态资源的访问路径。
 * 定制视图解析方式。
 * 自定义消息转换器处理 JSON/XML 等数据。
 * 实现文件上传、路径匹配规则等。
 * 相比于直接继承 WebMvcConfigurationSupport，实现 WebMvcConfigurer 不会完全覆盖 Spring 的默认 MVC 配置，而是进行补充和调整，因此它更适合大多数项目。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/12 9:21
 * @since 0.0.1
 */
@Slf4j
@Configuration
public class ToolWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("tool模块注册拦截器");
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**");    // 拦截所有路径
    }
}
