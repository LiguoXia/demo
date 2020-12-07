package com.liguo.demo.core;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;
import com.liguo.demo.core.pojo.converter.CarConverter;
import com.liguo.demo.core.pojo.entity.CarDO;
import com.liguo.demo.core.pojo.dto.CarDTO;
import com.liguo.demo.core.pojo.vo.Result;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.ArrayList;

@SpringBootApplication
@RestController
@Slf4j
@MapperScan(basePackages = "com.liguo.demo.core.dao")
public class DemoCoreApplication {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        SpringApplication app = new SpringApplication(DemoCoreApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        log.info("^-^####################################################^-^");
        log.info("^-^##### Core service was started,{} senconds.#####^-^", System.currentTimeMillis() - startTime);
        log.info("^-^####################################################^-^");
    }

    @GetMapping("/status")
    public Result status() {
        CarDO carDO = new CarDO();
        carDO.setId1(1);
        carDO.setBrand("BWM");
        carDO.setName("宝马");
        CarDTO carDTO = CarConverter.INSTANCE.carDO2DTO(carDO);
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
        hikariConfig.setJdbcUrl("jdbc:mysql://dubhedev-m.dbsit.sfcloud.local:3306/fp");
        hikariConfig.setUsername("fp");
        hikariConfig.setPassword("fp@200804");
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
                .fileName("资金计划表设计说明书").build();

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
                .description("资金计划表设计说明书")
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
