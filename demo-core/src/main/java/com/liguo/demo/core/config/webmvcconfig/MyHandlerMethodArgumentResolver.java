package com.liguo.demo.core.config.webmvcconfig;

import com.liguo.demo.core.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @LoginUser 注解 解析器
 * <p>这里都能抽离到comon包下,供个业务员微服务使用</p>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/30 12:04
 * @since 0.0.1
 */
@Slf4j
public class MyHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private DemoService demoService;

    public MyHandlerMethodArgumentResolver(DemoService demoService) {
        this.demoService = demoService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isSupport = parameter.hasParameterAnnotation(LoginUser.class) && parameter.getParameterType().equals(SysUser.class);
        return isSupport;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Class<?> parameterType = parameter.getParameterType(); // 获取参数类型
        String parameterName = parameter.getParameterName();   // 获取参数名称
        demoService.findAll();
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        SysUser sysUser = new SysUser();
        sysUser.setUsername("利国");
        return sysUser;
    }
}
