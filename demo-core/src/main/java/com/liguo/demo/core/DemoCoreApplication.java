package com.liguo.demo.core;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import com.liguo.demo.core.config.LoggingFilter;
import com.liguo.demo.core.pojo.converter.CarConverter;
import com.liguo.demo.core.pojo.dto.CarDTO;
import com.liguo.demo.core.pojo.entity.CarDO;
import com.liguo.demo.core.pojo.vo.Result;
import com.liguo.demo.core.study.beanLoadType.Import2.EnableCartoon;
import com.liguo.demo.core.thread.creatthread.ThreadUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.core.DebuggingClassWriter;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.ArrayList;

// 多数据源的时候会排除因为这个是单数据源装配,然后手动注入数据源
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
// 会自动加载当前类下所有文件
@SpringBootApplication
@RestController
@Slf4j
// 开启异步线程@Async注解能够生效
@EnableAsync
// 可以指定要扫描的Mapper类的包的路径, mapper接口加了@Mapper注解可以不需要添加扫描
//@MapperScan(basePackages = "com.liguo.demo.core.dao")
@EnableCartoon
// jetcache启用缓存的主开关
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = {"com.liguo.demo"})
// 引入配置文件
// @ImportResource(locations = "classpath:beans/beans-ws.xml")
// @ServletComponentScan 使用@ServletComponentScan注解后，
// Servlet（控制器）、Filter（过滤器）、Listener（监听器）可以直接通过@WebServlet、@WebFilter、@WebListener注解自动注册到Spring容器中，无需其他代码。
// 可以新建一个Config类，@ServletComponentScan加载该类上，这样只会扫描该目录或其子目录的
/**
 * @see LoggingFilter 用了@ServletComponentScan可以不用@Component注解
 */
public class DemoCoreApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // https://xie.infoq.cn/article/9a88ed4721c9c15c8b7bac6e7
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
        SpringApplication app = new SpringApplication(DemoCoreApplication.class);
        app.setBannerMode(Banner.Mode.CONSOLE);
        ConfigurableApplicationContext ctx = app.run(args);
        log.info("^-^####################################################^-^");
        log.info("^-^##### Core service was started,{} senconds.#####^-^", System.currentTimeMillis() - startTime);
        log.info("^-^####################################################^-^");
    }

    @GetMapping("/status")
    public Result status() {
        log.info("comming");
        ThreadUtil.sleep(600000);
        CarDO carDO = new CarDO();
        carDO.setId1(1);
        carDO.setBrand("BWM");
        carDO.setName("宝马");
        CarDTO carDTO = CarConverter.INSTANCE.carDO2DTO(carDO);
        log.info("complete");
        return Result.success(carDTO);
    }

    @GetMapping("/getSqlDoc")
    public Result getSqlDoc() {
        documentGeneration();
        return Result.success("数据库文档已经生成,请去D盘查看");
    }


    /**
     * 文档生成
     */
    void documentGeneration() {
        //数据源
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://????:3306/??");
        hikariConfig.setUsername("????");
        hikariConfig.setPassword("????");
        //设置可以获取tables remarks信息
        hikariConfig.addDataSourceProperty("useInformationSchema", "true");
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setMaximumPoolSize(5);
        DataSource dataSource = new HikariDataSource(hikariConfig);
        String fileOutputDir = "D:";
        //生成配置
        EngineConfig engineConfig = EngineConfig.builder()
                //生成文件路径
                .fileOutputDir(fileOutputDir)
                //打开目录
                .openOutputDir(true)
                //文件类型
                .fileType(EngineFileType.HTML)
                //生成模板实现
                .produceType(EngineTemplateType.freemarker)
                //自定义文件名称
                .fileName("表设计说明书").build();

        //忽略表
        ArrayList<String> ignoreTableName = new ArrayList<>();
        ignoreTableName.add("121");
        ignoreTableName.add("1212");
        //忽略表前缀
        ArrayList<String> ignorePrefix = new ArrayList<>();
        ignorePrefix.add("1212");
        //忽略表后缀
        ArrayList<String> ignoreSuffix = new ArrayList<>();
        ignoreSuffix.add("121");
        ProcessConfig processConfig = ProcessConfig.builder()
                //指定生成逻辑、当存在指定表、指定表前缀、指定表后缀时，将生成指定表，其余表不生成、并跳过忽略表配置
                //根据名称指定表生成
                .designatedTableName(new ArrayList<>())
                //根据表前缀生成
                .designatedTablePrefix(new ArrayList<>())
                //根据表后缀生成
                .designatedTableSuffix(new ArrayList<>())
                //忽略表名
                .ignoreTableName(ignoreTableName)
                //忽略表前缀
                .ignoreTablePrefix(ignorePrefix)
                //忽略表后缀
                .ignoreTableSuffix(ignoreSuffix).build();
        //配置
        Configuration config = Configuration.builder()
                //版本
                .version("1.0.0")
                //描述
                .description("表设计说明书")
                //数据源
                .dataSource(dataSource)
                //生成配置
                .engineConfig(engineConfig)
                //生成配置
                .produceConfig(processConfig)
                .build();
        //执行生成
        new DocumentationExecute(config).execute();
    }
}
