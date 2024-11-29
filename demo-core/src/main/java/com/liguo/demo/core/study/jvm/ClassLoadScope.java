package com.liguo.demo.core.study.jvm;

import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/8/17 21:10
 * @since 0.0.1
 */
@Slf4j
public class ClassLoadScope {
    public static void main(String[] args) {
        System.out.println("-----------------------------------");
        String pathBoot =  System.getProperty("sun.boot.class.path");
        log.info("{}", pathBoot.replaceAll(";", System.lineSeparator()));
        System.out.println("-----------------------------------");
        String pathExt =  System.getProperty("java.ext.dirs");
        log.info("{}", pathExt.replaceAll(";", System.lineSeparator()));
        System.out.println("-----------------------------------");
        String pathApp =  System.getProperty("java.class.path");
        log.info("{}", pathApp.replaceAll(";", System.lineSeparator()));
    }
}
