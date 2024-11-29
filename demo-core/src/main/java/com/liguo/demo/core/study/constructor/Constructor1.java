package com.liguo.demo.core.study.constructor;

import com.liguo.demo.core.study.beanLoadType.Import2.CartoonCatAndMouse;
import com.liguo.demo.core.study.beanLoadType.Import2.CartoonProperties;

/**
 * 1 默认构造方法：如果没有定义任何构造方法，Java会自动生成一个无参构造方法。
 * 2 自定义构造方法：一旦定义了带参数的构造方法，默认的无参构造方法就不再存在。
 * 3 无参构造方法的用途：无参构造方法通常用于创建默认对象，或者在某些框架（如Hibernate、Spring）中需要无参构造来实例化对象。
 * 4 this关键字：可以使用this来调用当前类的其他构造方法，以实现构造方法的链式调用。
 * 5 父类构造方法调用：子类的构造方法可以通过super()调用父类的构造方法，必须是第一行代码。
 * 不可继承：构造方法不会被继承，但子类可以调用父类的构造方法。
 * 私有构造方法：可以用于实现单例模式或静态工厂方法。
 *
 * @see CartoonCatAndMouse#CartoonCatAndMouse(CartoonProperties)
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/10/22 20:33
 * @since 0.0.1
 */
public class Constructor1 {

    private String aa;

    public Constructor1(String a) {
        this.aa = a;
    }

    private Constructor1() {}
}
