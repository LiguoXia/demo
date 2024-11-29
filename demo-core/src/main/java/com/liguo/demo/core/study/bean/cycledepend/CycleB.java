package com.liguo.demo.core.study.bean.cycledepend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * tag：
 *
 * </pre>
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/11/20 10:18
 * @since 0.0.1
 */
@Service
public class CycleB {

    @Autowired
    private CycleA cycleA;
}
