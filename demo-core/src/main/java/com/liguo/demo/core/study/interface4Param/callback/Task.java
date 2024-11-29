package com.liguo.demo.core.study.interface4Param.callback;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/17 9:45
 * @since 0.0.1
 */
public class Task {
    public void execute(Callback callback) {
        // 执行任务
        System.out.println("Task is running...");
        // 完成后调用回调
        callback.onComplete();
    }
}
