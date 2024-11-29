package com.liguo.demo.core.controller;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * jetCache接口
 *
 * @author xialiguo0212@gmail.com
 * @version 0.0.1
 * @date 2023/12/29 16:42
 * @since 0.0.1
 */
@Api(tags = "jetCache接口")
@Slf4j
@RestController
@RequestMapping("/jetCache")
public class JetCacheController {

    @CreateCache(name = "jetCache_", expire = 120, cacheType = CacheType.LOCAL)
    private Cache<String, String> jetCache;

    @ApiOperation("测试")
    @PostMapping("/putCache")
    public void putCache() {
        jetCache.put("jetCacheKey1", "缓存值");

        String cacheValue = jetCache.get("jetCacheKey1");
        log.info("缓存结果:{}", cacheValue);
    }


}
