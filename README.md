## SpringCloud 基础框架

### 1 工程结构

demo-parent:父工程,所有jar包的版本管理放在这个模块

demo-skeleton:骨架工程，所有的接口二方包放在该模块中作为它的子模块

   -demo-core-skeleton:核心业务员接口包

   -demo-consume-skeleton:消费接口包

demo-eureka:注册中心

demo-auth:鉴权认证

demo-getway:网关，开放接口统一加解密可放在这里

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

