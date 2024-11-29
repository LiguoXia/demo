package com.liguo.demo.consume.controller;

import com.liguo.demo.consume.pojo.vo.UserPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * redis控制器
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2024/9/17 20:57
 * @since 0.0.1
 */
@Api(tags = "redis操作接口")
@RestController
@RequestMapping("/redis")
@Slf4j
public class RedisController {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @ApiOperation("putObject")
    @GetMapping("/object")
    public String putObject(){
        UserPojo user = new UserPojo("001", "zhangSan","123456");
        // 序列化对象
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserPojo.class));
        this.redisTemplate.opsForValue().set("cookie@001", user,30, TimeUnit.SECONDS);
        System.out.print("/Object:");
        System.out.println(this.redisTemplate.opsForValue().get("cookie@001").toString());
        return "1";
    }
    @GetMapping("/list")
    public String putList(){
        this.redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserPojo.class));
        this.redisTemplate.opsForList().leftPush("cookie@002", new UserPojo("001", "zhangSan","123456"));
        System.out.print("/List:");
        System.out.println(this.redisTemplate.opsForList().range("cookie@002", 0, -1));
        return "1";
    }
    @GetMapping(value = "/key")
    public String putKey(String key, String value){
        this.redisTemplate.opsForValue().set(key,value,30, TimeUnit.SECONDS);
        System.out.print("/Key:");
        System.out.println(this.redisTemplate.opsForValue().get(key));
        return "1";
    }

    @GetMapping("/map")
    public String putMap(){
        Map<String, String> myMap = new HashMap<>();
        myMap.put("name", "zhangSan");
        myMap.put("age", "20");
        this.redisTemplate.opsForHash().putAll("map", myMap);
        System.out.print("/Map:");
        System.out.println(this.redisTemplate.opsForHash().get("map", "name"));
        return "1";
    }
}
