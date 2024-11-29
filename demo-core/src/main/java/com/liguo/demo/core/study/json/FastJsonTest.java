package com.liguo.demo.core.study.json;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.liguo.demo.core.pojo.entity.EntityTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * dsc
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2022/3/23 22:39
 * @since 0.0.1
 */
@Slf4j
public class FastJsonTest {
    public static void main(String[] args) {
        EntityTemplate entityTemplate1 = new EntityTemplate();
        entityTemplate1.setId(1l);
        entityTemplate1.setCreateTime(DateUtil.date());

        // 对象 集合 转json串
        String str = JSON.toJSONString(entityTemplate1);
        log.info("对象转JSON串:{}", str);

        // json字符串转对象
        EntityTemplate entityTemplate = JSON.parseObject(str, EntityTemplate.class);
    }
}
