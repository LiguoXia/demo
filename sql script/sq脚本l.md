### 建表语句

```sql
create table demo
(
    id          bigint auto_increment comment 'id'
        primary key,
    zifuchuan   varchar(28)                  DEFAULT NULL COMMENT '字符串',
    riqi        varchar(8)                   DEFAULT NULL COMMENT 'yyyyMMdd日期',
    shijian     varchar(100)                 DEFAULT NULL COMMENT 'hh:mm:ss时间',
    zhuangtai   tinyint(1) unsigned not null DEFAULT 0 COMMENT '状态字段',
    zhuangtai2  varchar(2)          not null DEFAULT '00' COMMENT '状态字段',
    shuzhi      int(11)                      DEFAULT NULL COMMENT '数值',
    bool        tinyint unsigned    not null default 0 comment '布尔',
    create_time timestamp                    default CURRENT_TIMESTAMP not null comment '创建时间 yyyy-MM-dd hh:mm:ss',
    update_time timestamp                    default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '修改时间 yyyy-MM-dd hh:mm:ss',
    created_by  varchar(20)                  DEFAULT NULL comment '创建人',
    modified_by varchar(20)                  DEFAULT NULL comment '修改人',
    del_flag    bigint                       default '0' comment '删除标识 id:删除 0：未删除',
    version     int                          default '0' comment '版本号',
    remark      varchar(100)                 DEFAULT NULL comment '备注'
)
    comment '样表' charset = utf8mb4;
```

