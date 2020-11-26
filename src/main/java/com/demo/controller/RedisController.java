package com.demo.controller;

import com.demo.entity.User;
import com.demo.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/26 17:06
 */
@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    private final RedisUtil redisUtil;

    @GetMapping("/getRedis")
    public void getRedis() {

        redisUtil.setCacheObject("aaa", User.builder().age(1).id(1).name("aaa").sex("男").build());

        Object object = redisUtil.getCacheObject("aaa");
        System.out.println("object = " + object);
    }

}
