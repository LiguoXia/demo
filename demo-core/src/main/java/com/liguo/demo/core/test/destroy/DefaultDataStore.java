package com.liguo.demo.core.test.destroy;

import com.liguo.demo.core.factory.DemoThreadFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/25 21:42
 * @since 0.0.1
 */
@Slf4j
@Service
public class DefaultDataStore implements DisposableBean {

    private final ExecutorService executorService = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(200), new DemoThreadFactory("UploadVideo"));

    /**
     * Invoked by the containing {@code BeanFactory} on destruction of a bean.
     *
     * @throws Exception in case of shutdown errors. Exceptions will get logged
     *                   but not rethrown to allow other beans to release their resources as well.
     */
    @Override
    public void destroy() throws Exception {
        log.info("准备优雅停止应用使用 DisposableBean");
        executorService.shutdown();
    }
}
