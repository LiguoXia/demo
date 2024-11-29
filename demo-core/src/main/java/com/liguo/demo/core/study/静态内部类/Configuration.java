package com.liguo.demo.core.study.静态内部类;

/**
 * 命名空间管理： 静态内部类提供了一个独立的命名空间，有助于组织和管理相关的类。这对于避免命名冲突和提高代码的可维护性很有帮助。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/12 20:12
 * @since 0.0.1
 */
public class Configuration {
    // 外部类的配置项

    // 静态内部类用于管理数据库相关的配置项
    public static class DatabaseConfig {
        // 数据库配置项
    }

    // 静态内部类用于管理日志相关的配置项
    public static class LoggingConfig {
        // 日志配置项
    }
}
