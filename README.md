## SpringCloud 基础框架

### 1 工程结构

demo-parent:父工程,所有jar包的版本管理放在这个模块

demo-skeleton:骨架工程，所有的接口二方包放在该模块中作为它的子模块

   -demo-core-skeleton:核心业务员接口包

   -demo-consume-skeleton:消费接口包

demo-eureka:注册中心

demo-auth:鉴权认证

demo-gateway:网关，开放接口统一加解密可放在这里

demo-core:核心业务微服务

demo-consume:消费微服务



工程包模块：

annotation:注解

cache:缓存

config:配置

controller:控制层

service:service接口层

  --impl:实现层

dao:数据库操作层

fegin:微服务间调用

  --client:客户端

  --server:服务端

pojo:对象层

  --do:此对象与数据库表结构一一对应，通过 DAO 层向上传输数据源对象

  --dto:数据传输对象， Service 或 Manager 向外传输的对象

  --bo:业务对象， 可以由 Service 层输出的封装业务逻辑的对象

  --query:查询对象，各层接收上层的查询请求。 注意超过 2 个参数的查询封装，禁止用 Map 

  --vo:显示层对象，通常是 Web 向模板渲染引擎层传输的对象

task:定时任务

cnstants:常量

enum:枚举

### 2 开发规范

#### 2.1 数据库规范

- 所有表必须加上create_time,update_time,created_by,modified_by四个字段

- 数据库表之间使用主键关联，不要使用CODE编码之类的做关联
- 状态字段或者枚举值字段数据库统一用tinyint类型，用数字1,2,3,4等作为值存储

#### 2.2 注释规范

#### 2.3 代码分层规范

针对于数据库表的增删查改代码，请使用mybatis-plus代码生成器生成代码。
   mapper接口保存方法已save开头，更新以update开头，查询已query或者get（单条）开头，删除以delete开头;(方便后续通过切面对方法进行增强，比如统一给create_time,update_time,created_by,modified_by等字段赋值)
  controller接口规范请参照以下代码

##### Web 层

##### Service 层

##### Manager 层

##### DAO 层

##### 第三方服务层

#### 2.4 日志打印规范

#### 2.5 异常处理规范

统一异常拦截

前端与接口调用统一返回格式

错误码定义

#### 2.6 代码提交规范

#### 2.7 参数验证方案

https://mp.weixin.qq.com/s/Esg8n3Aqu5moqjJEgTgw1Q

feat：新功能（feature）

fix：修补bug

docs：文档（documentation）

style： 格式（不影响代码运行的变动）

refactor：重构（即不是新增功能，也不是修改bug的代码变动）

test：增加测试

chore：构建过程或辅助工具的变动

示例：

feat：新增招行资金方接入

fix:修复手机号校验长度错误bug

subject是：commit 目的的简短描述，不超过50个字符。

#### 版本命名规范

参见document《Java项目版本管理规范.pdf》

### 3 字典表，枚举，业务配置表

#### 常量

#### 字典表

后端的枚举类型在前端展示时，需要用到字典表。后端返回的是枚举值，前端组件会拿枚举值去字典表找需要展示的数据。比如后端返回付款状态是PDNG,前端组件会查找字典表将PDNG展示成“待支付”。

#### 枚举

系统自定义的一些跟业务无关的不会经常改变的类型数据，而且系统会根据枚举值执行特定逻辑的，最好用枚举。比如付款状态

#### 业务配置表

跟业务强相关，而且数据会经常变化。就算数据量很少，也最好新建一张表存放这些数据。

### 4 分布式锁使用方式

### 5 分布式ID使用方式

### 7 消息队列使用方式

### 8 Redis使用方式

### 6 接口

#### 6.1 提供

#### 6.2 调用

### 7 依赖管理

### 8 国际化方案

#### 8.1 异常消息国际化

#### 8.2 校验器国际化

#### 8.3 数据库内容格式化

### 9 线程池

### 10 服务简介

#### 1 gateway

```
http://网关ip:网关端口/服务名/微服务路径
浏览器访问即可
http://localhost:8000/demo-core/test/hello
相当于访问：
http://localhost:8001/test/he11o
结论：网关会将服务名转换成具体服务的ip和端口，实际进行访问。
注意：此处的ip和端口是网关的ip和端口。
```

配置

查看过滤器：

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
  endpoint:
    health:
      ##默认是never
      show-details: ALWAYS
      enabled: true
    routes:
      enabled: true
      
http://localhost:8000/actuator/routes

http://localhost:8000/actuator/filters
```

### 11 springboot的配置

#### 如何查看SpringBoot的application配置项

1、[通过springboot的autoconfigure的spring.factories](https://blog.csdn.net/nail_candy/article/details/105408326)

2、[获取配置的两种方式](https://blog.51cto.com/wuge666/4961208)

#### beic参阅

```
MybatisPlusBaseConfig

FeignConfiguration

FrameworkEndpoint

GracefulShutdownConnectorCustomizer

MvcConfiguration
```



#### 1 分页

[分页](https://blog.csdn.net/qq_41359651/article/details/112260207)

配置分页插件

```java
@ConditionalOnClass({PaginationInterceptor.class})
@ConfigurationProperties(
    prefix = "mybatis-plus"
)
@Configuration
public class MybatisPlusBaseConfig {
    private boolean printSqlInLog = true;

    public MybatisPlusBaseConfig() {
    }

    @Bean
    @ConditionalOnProperty(
        name = {"mybatis-plus.print-sql"},
        havingValue = "true"
    )
    public PerformanceInterceptor performanceInterceptor() {
        return (new PerformanceInterceptor()).setWriteInLog(this.printSqlInLog);
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    public void setPrintSqlInLog(final boolean printSqlInLog) {
        this.printSqlInLog = printSqlInLog;
    }
}

```

3.4.0版本对此部分有更新，如果是旧版本升级，会出现分页失效问题，同时idea会提示PaginationInterceptor过时，新版本改用了`MybatisPlusInterceptor`

在高版本的SpringBoot中, 会提示这种写法已过时, 所以采用另一种写法 MybatisPlusInterceptor , 如下:

```java
/**
 * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
 */
@Bean
public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
    return interceptor;
}

@Bean
public ConfigurationCustomizer configurationCustomizer() {
    return configuration -> configuration.setUseDeprecatedExecutor(false);
}

```

### 12 自定义starter

#### 参阅

[你的springboot starter是如何生效的](https://cloud.tencent.com/developer/article/2310459)

[Spring Boot 自动配置的原理](https://springdoc.cn/springboot-auto-configuration-principle-analysis/)

### 13 开始一个springboot项目

#### 整合思路

1、导入对应starter

2、配置，一般会有提示，推荐使用独立命名空间的写法，如druid

#### 配置文件读取拆分

当然可以！在Spring Boot中，你可以将应用程序配置拆分成多个文件，这样可以更好地组织和管理配置信息。对于拆分Redis配置，你可以按照以下步骤操作：

1. **创建一个单独的配置文件：** 在`src/main/resources`目录下创建一个新的配置文件，比如`redis.properties`或者`redis.yml`，用来存放Redis相关的配置项。

2. **在主配置文件中引入新的配置文件：** 打开主配置文件（通常是`application.properties`或`application.yml`），使用`@ImportResource`或`spring.config.import`属性引入新的配置文件。比如：

   ```yaml
   spring:
     config:
       import: "classpath:redis.properties"
       
       
   或者某些特定框架的配置项，如
   spring.redis.redisson.file=classpath:redisson.yaml
   
   或者包含：
   spring：
     profiles:
       active: dev
       include: devMvc,devDB
   ```

   或者在Java配置类中使用`@ImportResource`注解：

   ```java
   @ImportResource("classpath:redis.properties")
   public class AppConfig {
       // ...
   }
   ```

   或者

   ```java
   @Slf4j
   @EnableDisconf
   @RestController
   @EnableBaseFeignInterceptor
   @EnableTransactionManagement
   @EnableCreateCacheAnnotation
   @MapperScan("com.sf.dubhe.fm.mapper")
   @EnableMethodCache(basePackages = "com.sf.dubhe.fm")
   @EnableFeignClients(basePackages = SysConstants.ROOT_PACKAGE)
   @ComponentScan(basePackages = {"com.sf.beic.*", "com.sf.dubhe.*"})
   @PropertySource(value = {"classpath:shardingJdbc.properties", "classpath:jetcache.properties"})
   @SpringBootApplication(exclude = {PropertyPlaceholderAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
   public class DubheFmApplication {
   
       public static void main(String[] args) {
           long startTime = System.currentTimeMillis();
           SpringApplication app = new SpringApplication(DubheFmApplication.class);
           app.setBannerMode(Banner.Mode.OFF);
           app.run(args);
           log.info("^-^###################################################^-^");
           log.info("^-^##### Fm service was started,{} senconds.#####^-^", System.currentTimeMillis() - startTime);
           log.info("^-^###################################################^-^");
       }
   
       @GetMapping("/status")
       public String status() {
           return "dubhe fm service ok...";
       }
   }
   ```

   

3. **在新的配置文件中添加Redis配置：** 在新的配置文件中，添加与Redis相关的配置项，例如：

   ```
   propertiesCopy coderedis.host=your_redis_host
   redis.port=your_redis_port
   redis.password=your_redis_password
   ```

   或者使用YAML格式：

   ```
   yamlCopy coderedis:
     host: your_redis_host
     port: your_redis_port
     password: your_redis_password
   ```

现在，你的Redis配置已经从主配置文件中拆分出来，使得代码更清晰、可维护。希望这能帮到你！

#### 1、整合mybatisplus

#### 2、整合durid

两种配置方式，第一种

```yml
# 应用名
spring:
  application:
    name: demo-core
  servlet:
    multipart:
      max-file-size: 500MB
      max-request-size: 500MB
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
  # 数据库设置，时区
  datasource:
    #url: jdbc:mysql://tidb.6713cf6b.3ce9e91.ap-northeast-1.prod.aws.tidbcloud.com:4000/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
    #username: root
    #password: demo20201001
    url: jdbc:mysql://192.168.18.22:3306/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
    #url: jdbc:mysql://192.168.18.23:3306/fp?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
    username: liguo
    password: xlg123456
    #    hikari:
    #      max-lifetime: 180000
    # 连接池配置
    type: com.alibaba.druid.pool.DruidDataSource
    # 下面为连接池的补充设置，应用到上面所有数据源中
    # 初始化大小，最小，最大(以下配置需要引入Druid Spring Boot Starter)
    druid:
      # 初始化时建立物理连接的个数
      initial-size: 5
      # 最小连接池数量
      min-idle: 5
      # 最大连接池数量
      max-active: 20
      # 配置获取连接等待超时的时间
      max-wait: 60000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      time-between-eviction-runs-millis: 60000
      # 配置一个连接在池中最小生存的时间，单位是毫秒
      min-evictable-idle-time-millis: 300000
      # 用来检测连接是否有效的sql，要求是一个查询语句
      validation-query: SELECT 1 FROM DUAL
      # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
      test-while-idle: true
      # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
      test-on-borrow: false
      # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
      test-on-return: false
      # 打开PSCache，并且指定每个连接上PSCache的大小
      # 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭
      pool-prepared-statements: true
      #   配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
      # 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。
      max-pool-prepared-statement-per-connection-size: 20
      filters: stat,wall,slf4j
      use-global-data-source-stat: true
      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
      connect-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000

      # druid监控地址 http://localhost:8001/admin/druid/index.html
      web-stat-filter:
        url-pattern: /*

      stat-view-servlet:
        enabled: true
        url-pattern: /admin/druid/*
        reset-enable: false
      filter:
        stat:
          log-slow-sql: true
          slow-sql-millis: 1000
      driver-class-name: com.mysql.cj.jdbc.Driver
```

第二种,推荐用

```yml
# 应用名
spring:
  application:
    name: demo-core
  servlet:
    multipart:
      max-file-size: 500MB
      max-request-size: 500MB
  jackson:
    date-format: yyyy-MM-dd HH:mm:ss
    time-zone: GMT+8
  # 数据库设置，时区
  datasource:
    # url: jdbc:mysql://tidb.6713cf6b.3ce9e91.ap-northeast-1.prod.aws.tidbcloud.com:4000/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
    # username: root
    # password: demo20201001
    # url: jdbc:mysql://192.168.18.22:3306/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
    # url: jdbc:mysql://192.168.18.23:3306/fp?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
    # username: liguo
    # password: xlg123456
    #    hikari:
    #      max-lifetime: 180000
    # 连接池配置
    type: com.alibaba.druid.pool.DruidDataSource
    # 下面为连接池的补充设置，应用到上面所有数据源中
    # 初始化大小，最小，最大(以下配置需要引入Druid Spring Boot Starter)
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://192.168.18.22:3306/demo?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=GMT%2B8
      username: liguo
      password: xlg123456
      # 初始化时建立物理连接的个数
      initial-size: 5
      # 最小连接池数量
      min-idle: 5
      # 最大连接池数量
      max-active: 20
      # 配置获取连接等待超时的时间
      max-wait: 60000
      # 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
      time-between-eviction-runs-millis: 60000
      # 配置一个连接在池中最小生存的时间，单位是毫秒
      min-evictable-idle-time-millis: 300000
      # 用来检测连接是否有效的sql，要求是一个查询语句
      validation-query: SELECT 1 FROM DUAL
      # 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
      test-while-idle: true
      # 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。
      test-on-borrow: false
      # 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能
      test-on-return: false
      # 打开PSCache，并且指定每个连接上PSCache的大小
      # 是否缓存preparedStatement，也就是PSCache。PSCache对支持游标的数据库性能提升巨大，比如说oracle。在mysql下建议关闭
      pool-prepared-statements: true
      #   配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
      # 要启用PSCache，必须配置大于0，当大于0时，poolPreparedStatements自动触发修改为true。
      max-pool-prepared-statement-per-connection-size: 20
      filters: stat,wall,slf4j
      use-global-data-source-stat: true
      # 通过connectProperties属性来打开mergeSql功能；慢SQL记录
      connect-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000

      # druid监控地址 http://localhost:8001/admin/druid/index.html
      web-stat-filter:
        url-pattern: /*

      stat-view-servlet:
        enabled: true
        url-pattern: /admin/druid/*
        reset-enable: false
      filter:
        stat:
          log-slow-sql: true
          slow-sql-millis: 1000
```

#### 3、分页

#### 4、统一异常拦截

#### 5、统一返回

#### 6、

### 14 运维

#### 1、运行springboot程序

package （maven 右上角闪电图标表示跳过test）

java -jar xxx.jar --server.port=8081  （这里的属性就是public static void main(String[] args) 的args）

参数优先级：[属性优先级](https://springdoc.cn/spring-boot/features.html#features.external-config)

#### 2、配置等级 4级

总的原则：叠加并覆盖

```
1．SpringBoot中4级配置文件
1级:file : config/application.yml【最高】
2级:file : application.yml
3级: classpath:config/application.yml【最低】
4级: classpath:application.yml

2. 作用:
1级与2级留做系统打包后设置通用属性，1级常用于运维经理进行线上整体项自部署方案调控
3级与4级用于系统开发阶段设置通用属性，3级常用于项目经理进行整体项自属性调控
```

#### 3、多环境开发

1、独立文件配置

2、同一个文件---区分

### 15 热部署

```
<!-- 热部署 (仅包括重启)-->
<!-- build - Build Project 激活 快捷键 Ctrl + F9-->
<!-- 配置自动build 1、setting - build - compiler - Build auto 2、 Ctrl+Shift+Alt+/ - registry - compiler.automake.allow.when.app.running-->
<!-- 重启（Restart）：自定义开发代码，包含类、页面、配置文件等，加载位置restart类加载器 -->
<!-- 重载（ReLoad）：jar包，加载位置base类加载器 -->
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-devtools</artifactId>
</dependency>
```

### 16 配置第三方bean

```java
@ConfigurationProperties(prefix = "aspect.log")

不仅可以为自己的配置属性绑定，还可以绑定第三方的bean
配合
@Bean
@ConfigurationProperties 支持松散绑定 @Value不支持，prefix这里的value全部要小写，并且最好是烤肉串模式 xx-xx


@Bean(name = TIDB_DATASOURCE_BEAN_NAME, autowireCandidate = false)
    @ConfigurationProperties(prefix = TIDB_DATASOURCE_PREFIX)
    public DruidDataSource tidb() {
    return new DruidDataSource();
}

@ConfigurationProperties具体做属性绑定，下面是开启属性绑定并设定对应的目标是什么
开启属性绑定：也可以在属性类上加上@Component
@EnableConfigurationProperties({FsOpenApiProperties.class})
    
两个特殊的单位绑定，时间和数据容量大小
@DurationUnit(ChronoUnit.HOURS）
private Duration serverTimeout:
@DataSizeUnit(DataUnit.MEGABYTES）
private Datasize datasize;
              
yaml支持各种进制的书写，字符串配置最好加上双引号
```

#### 属性配置校验

引入jar

```xml
<!-- 校验 -->
<dependency>
    <groupId>javax.validation</groupId>
    <artifactId>validation-api</artifactId>
</dependency>
```

配置类开启校验

```java
@Validated
```

校验属性

使用@Import注解加载当前测试类专用的配置	

### 本地部署中间件

1、启动mysql 26                             3306                   systemctl start mysqld

2、docker启动rocketmq  33     8080、9876 、10911         docker start rmqnamesrv rocketmq-broker rmqconsole

3、启动canal 36   

4、启动redis 29                               6379                     redis-server redis.conf

5、启动eureka

| 服务         | 版本   | ip               |      | 端口                      | 启动命令                                           | 控制台地址                 | 说明                                |
| ------------ | ------ | ---------------- | ---- | ------------------------- | -------------------------------------------------- | -------------------------- | ----------------------------------- |
| mysql        | 8.0.32 | 192.168.18.22    | 22   | 3306                      | systemctl start mysqld                             |                            | 本地单机部署                        |
| redis        | 7.2.0  | 192.168.18.34    | 25   | 6379                      | redis-server redis.conf                            |                            | 本地单机部署                        |
| rocketmq     | 5.1.3  | 192.168.18.33-35 | 23   | 8080、9876 、10911        | docker start rmqnamesrv rocketmq-broker rmqconsole | http://192.168.18.33:8080/ | docker部署，server、broker、console |
| nacos        | 2.4.2  | 192.168.18.33    |      | 8848                      | docker start nacos-standalone                      | http://192.168.18.33:8848/ | docker部署                          |
| canal        | 1.1.8  | 192.168.18.36    |      | 8089、11110、11111、11112 | ./startup.sh、 ./startup.sh local                  | http://192.168.18.36:8089/ | 本地部署，admin、server             |
| zookeeper    | 3.9.2  | 192.168.18.37    | 24   | 2181                      |                                                    |                            |                                     |
| gitlab       | 17.3.3 | 192.168.18.37    | 24   | 80、443、222              |                                                    |                            |                                     |
| elasticsearc | 8.7.0  | 192.168.18.37    | 24   | 9200、9300                |                                                    |                            |                                     |
| kafka        |        | 192.168.18.37    | 24   |                           |                                                    |                            |                                     |

## Springboot版本说明

### 本项目版本

| 依赖                                       | 版本          |      |
| ------------------------------------------ | ------------- | ---- |
| spring-boot-starter-web                    | 2.3.3.RELEASE |      |
| spring-web                                 | 5.2.8.RELEASE |      |
| spring-cloud-starter-netflix-eureka-client | 2.2.4.RELEASE |      |
| spring-cloud-starter-openfeign             | 2.2.5.RELEASE |      |



### 所有版本

Spring Boot 2.7 是最后一个支持 JDK 8 的版本，它在2023年 11 月 18 号停止维护，
