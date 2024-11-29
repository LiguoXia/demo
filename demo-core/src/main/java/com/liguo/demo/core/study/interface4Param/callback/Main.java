package com.liguo.demo.core.study.interface4Param.callback;

/**
 * 接口常用于回调机制，允许某些操作在完成时调用提供的接口方法。例如，使用监听器模式来处理事件。
 *
 * 示例：
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/17 9:45
 * @since 0.0.1
 */
public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        task.execute(new Callback() {
            @Override
            public void onComplete() {
                System.out.println("Task completed.");
            }
        });
    }
}
