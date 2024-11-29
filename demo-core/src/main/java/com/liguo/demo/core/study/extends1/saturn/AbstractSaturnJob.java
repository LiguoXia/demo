package com.liguo.demo.core.study.extends1.saturn;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/5/22 23:34
 * @since 0.0.1
 */
@Slf4j
public abstract class AbstractSaturnJob implements Saturn{

    @Override
    public void doJob() {
        log.info("start");
        doJob1();
        log.info("end");
    }

    protected abstract void doJob1();
}
