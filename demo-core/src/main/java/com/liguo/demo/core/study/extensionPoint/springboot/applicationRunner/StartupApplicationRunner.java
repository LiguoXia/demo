package com.liguo.demo.core.study.extensionPoint.springboot.applicationRunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 应用场景
 * 1、启动后参数解析
 * ApplicationRunner 可以更清晰地处理启动时传递的参数，特别是带选项的参数（--key=value），可以用来初始化一些系统配置或者执行特定逻辑。
 * <p>
 * 2、系统初始化
 * 当应用程序启动后，需要执行某些初始化任务，比如加载配置文件、设置缓存、连接外部服务（数据库、消息队列等），ApplicationRunner 是一个合适的选择。
 * <p>
 * 3、检查系统依赖
 * 在应用启动后，使用 ApplicationRunner 来验证依赖服务的可用性，比如检查数据库连接、外部 API 服务等是否运行正常。
 * <p>
 * 4、调度任务
 * 如果系统涉及定时任务或需要在后台运行某些任务，可以通过 ApplicationRunner 在应用启动时启动这些任务。
 * <p>
 * 5、数据加载或迁移
 * 在应用启动后通过 ApplicationRunner 进行数据迁移、加载初始数据或者执行一些启动时的数据库操作。
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/26 20:22
 * @since 0.0.1
 */
@Component
public class StartupApplicationRunner implements ApplicationRunner {
    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("应用启动后执行的逻辑");

        // 处理启动参数
        if (args.containsOption("config")) {
            System.out.println("配置参数: " + args.getOptionValues("config"));
        }

        // 执行初始化逻辑，如加载数据，连接外部服务等

        if (args.containsOption("config")) {
            System.out.println("启动时传递的配置参数: " + args.getOptionValues("config"));
        }

        if (args.containsOption("mode")) {
            System.out.println("启动模式: " + args.getOptionValues("mode"));
        }
    }
}
