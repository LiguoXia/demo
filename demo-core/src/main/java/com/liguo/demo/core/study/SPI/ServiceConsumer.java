package com.liguo.demo.core.study.SPI;

import com.liguo.demo.core.thread.creatthread.ThreadUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ServiceLoader;

/**
 * SPI 当服务的提供者提供了一种接口的实现之后，需要在classpath下的META-INF/services/目录里创建一个以服务接口命名的文件
 * 这个文件里的内容就是这个接口的具体的实现类。当其他的程序需要这个服务的时候，
 * 就可以通过查找这个jar包（一般都是以jar包做依赖）的META-INF/services/中的配置文件，配置文件中有接口的具体实现类名，
 * 可以根据这个类名进行加载实例化，就可以使用该服务了。JDK中查找服务的实现的工具类是：java.util.ServiceLoader。
 * 参考mysql 驱动 java.sql.Driver
 *
 * https://mp.weixin.qq.com/s/F46e4jAut541RjLA3KVfOw
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/1/22 11:24
 * @since 0.0.1
 */
public class ServiceConsumer {
    @Autowired
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            ServiceLoader<MyService> serviceLoader = ServiceLoader.load(MyService.class);

            for (MyService service : serviceLoader) {
                service.doSomething();
            }
            ThreadUtil.sleep(20000);
        }

    }
}
