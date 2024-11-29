package com.liguo.demo.core.study.extends1.saturn;

import lombok.extern.slf4j.Slf4j;

/**
 * 模板方法,通用的功能放在抽象类中,mybatis源码中Executor也是这样实现,BaseExecutor
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/22 23:36
 * @since 0.0.1
 */
@Slf4j
public class MyJob extends AbstractSaturnJob{
    @Override
    protected void doJob1() {
        log.info("do my job");
    }
}
