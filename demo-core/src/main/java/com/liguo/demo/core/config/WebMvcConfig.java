package com.liguo.demo.core.config;

import com.liguo.demo.core.config.webmvcconfig.MyHandlerMethodArgumentResolver;
import com.liguo.demo.core.interceptor.MyCustomInterceptor;
import com.liguo.demo.core.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

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
public class WebMvcConfig implements WebMvcConfigurer {
    /**
     * Helps with configuring HandlerMappings path matching options such as trailing slash match,
     * suffix registration, path matcher and path helper.
     * Configured path matcher and path helper instances are shared for:
     * <ul>
     * <li>RequestMappings</li>
     * <li>ViewControllerMappings</li>
     * <li>ResourcesMappings</li>
     * </ul>
     *
     * @param configurer
     * @since 4.0.3
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {

    }

    /**
     * 配置内容协商，可以设置默认的媒体类型和默认的视图内容类型。
     * Configure content negotiation options.
     *
     * @param configurer
     */
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

    }

    /**
     * 配置异步请求的处理
     * Configure asynchronous request handling options.
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {

    }

    /**
     * 配置静态资源的处理，如对默认的Servlet的处理方式。
     * Configure a handler to delegate unhandled requests by forwarding to the
     * Servlet container's "default" servlet. A common use case for this is when
     * the {@link DispatcherServlet} is mapped to "/" thus overriding the
     * Servlet container's default handling of static resources.
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

    }

    /**
     * Add {@link Converter Converters} and {@link Formatter Formatters} in addition to the ones
     * registered by default.
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {

    }

    /**
     * 添加Spring MVC生命周期拦截器，用于控制器方法调用和资源处理程序请求的预处理和后处理。拦截器可以注册为应用于所有请求，也可以被限制为URL模式的一个子集。
     * 拦截器的拦截顺序，是按照Web配置文件中注入拦截器的顺序执行的。
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("注册拦截器");
        registry.addInterceptor(new MyCustomInterceptor())
                .addPathPatterns("/**")    // 拦截所有路径
                .excludePathPatterns("/login", "/signup");  // 排除某些路径
    }

    /**
     * 添加静态资源处理，配置处理静态资源的映射。
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

    }

    /**
     * 配置跨域资源共享（CORS）。
     * Configure cross origin requests processing.
     *
     * @param registry
     * @since 4.2
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {

    }

    /**
     * 添加视图控制器，简化页面跳转的配置。
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }

    /**
     * 配置视图解析器，影响视图解析的行为。
     * Configure view resolvers to translate String-based view names returned from
     * controllers into concrete {@link View}
     * implementations to perform rendering with.
     *
     * @param registry
     * @since 4.1
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
    }

    /**
     * 参数解析器
     *
     * @param resolvers initially an empty list
     */
    @Autowired
    private DemoService demoService;
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        log.info("注册参数解析器");
        resolvers.add(new MyHandlerMethodArgumentResolver(demoService));
    }

    /**
     * Add handlers to support custom controller method return value types.
     * <p>Using this option does not override the built-in support for handling
     * return values. To customize the built-in support for handling return
     * values, configure RequestMappingHandlerAdapter directly.
     *
     * @param handlers initially an empty list
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {

    }

    /**
     * 配置消息转换器，用于请求和响应的消息转换。
     *
     * @param converters initially an empty list of converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("添加消息转换器");
    }

    /**
     * 扩展消息转换器，允许在默认消息转换器列表的末尾添加自定义的消息转换器。
     * A hook for extending or modifying the list of converters after it has been
     * configured. This may be useful for example to allow default converters to
     * be registered and then insert a custom converter through this method.
     *
     * @param converters the list of configured converters to extend.
     * @since 4.1.3
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /**
     * Configure exception resolvers.
     * <p>The given list starts out empty. If it is left empty, the framework
     * configures a default set of resolvers, see
     * {@link WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List, ContentNegotiationManager)}.
     * Or if any exception resolvers are added to the list, then the application
     * effectively takes over and must provide, fully initialized, exception
     * resolvers.
     * <p>Alternatively you can use
     * {@link #extendHandlerExceptionResolvers(List)} which allows you to extend
     * or modify the list of exception resolvers configured by default.
     *
     * @param resolvers initially an empty list
     * @see #extendHandlerExceptionResolvers(List)
     * @see WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List, ContentNegotiationManager)
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    /**
     * Extending or modify the list of exception resolvers configured by default.
     * This can be useful for inserting a custom exception resolver without
     * interfering with default ones.
     *
     * @param resolvers the list of configured resolvers to extend
     * @see WebMvcConfigurationSupport#addDefaultHandlerExceptionResolvers(List, ContentNegotiationManager)
     * @since 4.3
     */
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    /**
     * Provide a custom {@link Validator} instead of the one created by default.
     * The default implementation, assuming JSR-303 is on the classpath, is:
     * {@link OptionalValidatorFactoryBean}.
     * Leave the return value as {@code null} to keep the default.
     */
    @Override
    public Validator getValidator() {
        return null;
    }

    /**
     * Provide a custom {@link MessageCodesResolver} for building message codes
     * from data binding and validation error codes. Leave the return value as
     * {@code null} to keep the default.
     */
    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return null;
    }
}
