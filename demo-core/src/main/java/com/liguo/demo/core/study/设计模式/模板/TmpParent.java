package com.liguo.demo.core.study.设计模式.模板;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/30 17:00
 * @since 0.0.1
 */
public class TmpParent {

    public void refresh() {
        step1();
        step2();
        step3();
    }

    private void step1() {
        System.out.println("step1");
    }

    private void step2() {
        System.out.println("step2");
    }

    protected void step3() {
        System.out.println("step3");
    }
}
