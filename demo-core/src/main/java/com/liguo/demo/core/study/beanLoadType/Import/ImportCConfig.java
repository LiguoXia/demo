package com.liguo.demo.core.study.beanLoadType.Import;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 可以用@Import导入配置类，配置类自身bean会加载，定义的bean也会加载
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 15:03
 * @since 0.0.1
 */
@Configuration
@Import(ImportClassC.class)
public class ImportCConfig {
}
