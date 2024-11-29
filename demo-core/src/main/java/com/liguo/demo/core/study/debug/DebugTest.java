package com.liguo.demo.core.study.debug;

import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * debug技巧
 * <p>1.step into之后选中某一个方法就可以进入</p>
 * <p>2.点击Drop Frame可以回到上一处断点</p>
 * <p>3.循环开始出断点,右键可选择进入断点的条件</p>
 * <p>4.shift+鼠标左键debug模式下打印信息</p>
 * <p>5.字段断点：在字段定义处鼠标左键添加断点（会出现「眼睛」的图标）,在「眼睛」图标上鼠标右键,在弹框中勾选上 Field access 和 Field modification 两个选项</p>
 * <p>6.异常断点:catch住异常,知道栈顶异常类型,红色双圈-选择Java Exception Breakpoints - 添加对应的异常</p>
 * <p>7.方法接口,一个接口的方法可能被多个子类实现，当运行时，需要查看调用栈逐步定位实现类,IDEA 同样支持在接口方法上添加断点,鼠标左键在方法处点击断点（♦形状）断点上鼠标右键</p>
 * <p>8.右击debug堆栈信息右击,可以强制返回执行值</p>
 * <p>9.右击debug堆栈信息右击,可以强制抛出异常</p>
 *
 * 多线程debug
 * debug是分级别的：all, thread
 * 默认是all，然后只会debug到本线程的断点，修改成thread就可以swap Thread 来debug了
 * 如果想进入Thread Debug，就必须所有的断点都是Thread 类型的，否则还是进不去
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/23 22:44
 * @since 0.0.1
 */
@Slf4j
public class DebugTest {
    public static void main(String[] args) {
        // step into之后选中某一个方法就可以进入
        // 点击Drop Frame可以回到上一处断点
        String a = func1(func2(func3("param3")));
        log.info("a:{}", a);

        for (int i = 0; i < 100; i++) {
            // 循环开始出断点,右键可选择进入断点的条件
        }
        String test = "test";
        modifyParam1(test);
        log.info("test：{}", test);
    }

    private static String func3(String param) {
        int a = 1 + 1;
        return param;
    }

    private static String func2(String param) {
        return param;
    }

    private static String func1(String param) {
        return func4(param);
    }

    private static String func4(String param) {
        return func5(param);
    }

    private static String func5(String param) {
        return param;
    }

    private static void modifyParam1(String param) {
        param = param + "modify1";
    }

    private static void modifyParam2(String param) {
        param = param + "modify2";
    }
}
