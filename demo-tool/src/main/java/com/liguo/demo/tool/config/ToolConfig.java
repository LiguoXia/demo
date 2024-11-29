package com.liguo.demo.tool.config;

import com.liguo.demo.tool.SpringBeanUtil;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 为了扫描这个工程
 * @see SpringBeanUtil
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 12:14
 * @since 0.0.1
 */
@Configuration
@ComponentScan(value = {"com.liguo.demo.tool"})
public class ToolConfig {
}
