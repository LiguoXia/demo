package com.liguo.demo.core.study.beanLoadType.ImportResource;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/27 14:56
 * @since 0.0.1
 */
public class XmlImportService {
    private String name;

    public XmlImportService(String name) {
        this.name = name;
    }
    public void printName() {
        System.out.println("User name is: " + name);
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
