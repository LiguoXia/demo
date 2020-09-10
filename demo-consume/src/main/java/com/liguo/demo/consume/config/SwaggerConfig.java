package com.liguo.demo.consume.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger接口文档
 * <p>接口地址http://localhost:8002/swagger-ui.html</p>
 *
 * @author liguo
 * @create 2020-09-10
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //配置Swagger的Bean实例
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.liguo.demo.core.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    //配置API的基本信息（会在http://项目实际地址/swagger-ui.html页面显示）
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试API文档标题")
                .description("测试api接口文档描述")
                .termsOfServiceUrl("http://www.liguo.com")
                .version("1.0")
                .build();
    }
}
