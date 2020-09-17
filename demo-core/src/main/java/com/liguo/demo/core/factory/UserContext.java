package com.liguo.demo.core.factory;

import com.liguo.demo.core.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Spring会自动地将形如(@Service后面的名称，实现该接口的类)注入到该userMap中
 * 在启动后，userMap中就存在两个元素，("student"，StudentServiceImpl)与("teacher"，TeacherServiceImpl)
 * getUserService方法返回userMap中key=type的UserService对象
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2020/9/17
 * @since 0.0.1
 */
@Service
public class UserContext {
    @Autowired
    Map<String, IUserService> userMap;

    public IUserService getUserService(String type) {
        return userMap.get(type);
    }
}
