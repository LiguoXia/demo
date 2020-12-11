package com.liguo.demo.core.util;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Thread pool tools
 *
 * @author 01395755[xialiguo]
 * @version 1.1
 * @date 2020/12/10 17:56
 * @since 1.1
 */
@Slf4j
public class ThreadPoolUtil {
    /**
     * 组合多个CompletableFuture为一个CompletableFuture,所有子任务全部完成,组合后的任务才会完成,带返回值,可直接get.
     *
     * @param futures CompletableFuture集合
     * @param <T>
     * @return CompletableFuture
     */
    public static <T> CompletableFuture<List<T>> sequence(List<CompletableFuture<T>> futures) {
        //1.构造一个空CompletableFuture，子任务数为入参任务list size
        CompletableFuture<Void> allDoneFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]));
        //2.流式（总任务完成后，每个子任务join取结果，后转换为list）
        return allDoneFuture.thenApply(v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList()));
    }

    /**
     * 阻塞主线程
     *
     * @param futures CompletableFuture集合
     * @param <T>
     */
    public static <T> void blockMainThread(List<CompletableFuture<T>> futures) {
        try {
            sequence(futures).get();
        } catch (Exception e) {
            log.error("中断异常", e);
        }
    }
}
