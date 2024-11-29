package com.liguo.demo.core.study.加解密;


import com.alibaba.druid.filter.config.ConfigTools;

/**
 * dsc
 * <p>https://blog.csdn.net/fanbaodan/article/details/102854106</p>
 *
 * // java -cp druid-1.1.23.jar com.alibaba.druid.filter.config.ConfigTools xlg123456
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/7/20 9:13
 * @since 0.0.1
 */
public class DuridJieMi {
    public static void main(String[] args) {
        // String pwd = "IHF1krUF5L8io91XdEiM0QKD1zpNIJ+3O1oEFwlEL8dtZK4A7SERaXH+hyOzIqekwDb+8Bn0353qcK7UWW1yqg==";
        // String pub = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIOv1o68BENgWPNDNqF0iE4kFYMYPAnBS2GpgkYwEselxHgLbog6WAJYQrR3jhhORgVfcyJkt8iiZL7fyRb0E90CAwEAAQ==";

        String pwd = "V4Zk6qzaqmLVw1AxYTeGLUsevrXjBrWnemFKO3YMohH48fH2hROetQSziBqRwLMHhHGKZS+BhfegAGLqeIXS+g==";
        String pub = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKzAOx/IEXHUhoandAOxcja+xI7BGjJLjaLk916KaAmvZyA6pzSuIhIrWZ4yH/7fDa1699YqEEZUGYfuSJv0YEECAwEAAQ==";
        try {
            String depwd = ConfigTools.decrypt(pub, pwd);
            System.out.println(depwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
