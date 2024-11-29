package com.liguo.demo.core.study.extensionPoint.springboot.commandLineRunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 应用场景
 * 1、应用启动后的初始化任务
 * 在应用启动后需要立即执行某些操作，如加载初始数据、检查系统状态、执行定时任务的注册等。
 * <p>
 * 2、验证配置文件
 * 可以在 Spring Boot 应用启动后，验证加载的配置信息是否正确，检查必要的外部服务（如数据库、缓存服务等）是否可用。
 * <p>
 * 3、加载或预处理数据
 * 在应用启动时，可以通过 CommandLineRunner 从数据库中加载必要的数据到内存缓存中，或者进行一些数据预处理工作。
 * <p>
 * 4、调度任务
 * 如果你的应用涉及到调度任务或其他后台服务，CommandLineRunner 可以用于在应用启动后立即启动任务调度器或工作线程。
 * <p>
 * 5、系统初始化检查
 * 在应用启动后，检查必要的依赖（如外部服务、消息队列等），并对系统的整体状态进行健康检查。
 * <p>
 * 6、执行数据库迁移
 * 在应用启动后执行数据库的 schema 迁移或者进行数据库的版本更新。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/26 20:16
 * @since 0.0.1
 */
// @Order(1)
@Component
public class StartupRunner implements CommandLineRunner, Ordered {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming main method arguments 接收应用启动时传递的参数
     * @throws Exception on error
     */
    @Override
    public void run(String... args) throws Exception {
        // 这里编写你希望在应用启动后执行的代码
        System.out.println("应用启动完成后执行的逻辑");
        // 初始化资源，启动任务，加载数据等
    }

    @Override
    public int getOrder() {
        return 1; // 返回的数值越小，优先级越高
    }
}
