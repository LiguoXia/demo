package com.liguo.demo.core.study.静态内部类;

/**
 * 事件监听器： 静态内部类常用于实现事件监听器，尤其是在GUI编程中。每个事件监听器可以独立于外部类的实例存在。CarFactory
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/12 20:13
 * @since 0.0.1
 */
public class Button {
    // 外部类的按钮实现

    // 静态内部类用于实现按钮点击事件监听器
    public static class ClickListener {
        // 处理按钮点击事件的逻辑
    }
}
