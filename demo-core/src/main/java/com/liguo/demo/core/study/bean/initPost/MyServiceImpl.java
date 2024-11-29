package com.liguo.demo.core.study.bean.initPost;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * InitializingBean的作用是Bean注入到Spring容器且初始化后，执行特定业务化的操作。Spring允许容器中的Bean，在Bean初始化完成后或者Bean销毁前，执行特定业务化的操作，常用的实现方式有以下三种：
 *
 * <p>通过实现InitializingBean/DisposableBean接口来处理初始化后/销毁前的操作；
 * <p>通过标签的init-method/destroy-method属性处理初始化后/销毁前的操作；
 * <p>在指定方法上加上@PostConstruct或@PreDestroy注解来处理初始化后/销毁前的操作。
 *
 * <p>日志顺序</p>
 * @see MyPostProcessorBean
 * <pre>
 *     Bean:myServiceImpl初始化之前执行操作
 *     第一步执行
 *     initPost: PostConstruct method: myServiceImpl
 *     MyServiceImpl注入到spring容器且初始化后执行这里逻辑
 *     第二步骤：implements InitializingBean方法生成　 Bean:myServiceImpl初始化之后执行操作　
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/3/3 14:41
 * @since 0.0.1
 */
@Slf4j
@Service
public class MyServiceImpl implements InitializingBean {

    /**
     * Bean注入到Spring容器且初始化后执行
     * <p>常用于在 bean 初始化阶段执行一些特定的逻辑，比如初始化数据或资源。</p>
     * <p>与使用 @PostConstruct 注解类似</p>
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("MyServiceImpl注入到Spring容器且初始化后执行这里逻辑");
        log.info("第二步骤:implements InitializingBean方法生成");
    }

    @PostConstruct
    public void postConstructMethod() {
        log.info("第一步执行");
        // @PostConstruct：首先执行。所有依赖注入完成后，Spring 会先调用标记了 @PostConstruct 的方法。1
        log.info("initPost: PostConstruct method: myServiceImpl");
    }


    /**
     * 这里与当前类会生成两个MyServiceImpl实例分别执行
     * <p>实例化 - 初始化 - postConstructMethod - afterPropertiesSet</p>
     * <p>实例化 - 初始化 - postConstructMethod - init</p>
     *
     * @see MyConfiguration#myService()
     */
    private void init() {
        log.info("第二步骤:@Bean注解是生成实例初始化方法");
    }
}
