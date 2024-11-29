package com.liguo.demo.core.study.thread.interrupt.case2;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/31 10:46
 * @since 0.0.1
 */
public class InterruptSelfMainTest {
    public static void main(String[] args) {
        System.out.println("1111");
        // 在这段代码中，主线程没有进入任何可能响应中断的方法（如 Thread.sleep()、Object.wait() 等），
        // 所以中断状态的设置不会对后续执行产生直接影响。主线程将正常执行完毕。
        Thread.currentThread().interrupt();
        System.out.println("2222");
    }
}
