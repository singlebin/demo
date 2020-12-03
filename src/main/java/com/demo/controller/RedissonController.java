package com.demo.controller;

import com.demo.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @description: 分布式锁 @link{https://www.bilibili.com/video/BV1Yy4y1k79y?p=2}
 * @Author: wub CAP原则又称CAP定理，指的是在一个分布式系统中，一致性（Consistency）、可用性（Availability）、分区容错性（Partition tolerance）。CAP 原则指的是，这三个要素最多只能同时实现两点，不可能三者兼顾。
 * @Date: 2020/11/30 10:59
 */
@RestController
@RequestMapping("/redisson")
@RequiredArgsConstructor
public class RedissonController {

    private final RedisUtil redisUtil;
    private final Redisson redisson;
    private final String SOTCK_KEY = "stock:key:";

    /**
     * 超卖问题
     * synchronized 只针对于jvm（Tomcat）是加锁的，无法控制多个tomcat的并发问题
     * 假设库存有100件，并发来扣减库存
     */
    @GetMapping("/buy")
    public void buy() {
        int sum = (int) redisUtil.getCacheObject(SOTCK_KEY);
        RLock lock = redisson.getLock(SOTCK_KEY);
        // 扣减库存
        if (sum > 0) {
            lock.lock();
            int count = sum - 1;
            redisUtil.setCacheObject(SOTCK_KEY, count, 1000, TimeUnit.SECONDS);
            System.out.println("扣减库存成功，剩余库存count = " + count);

        } else {
            System.out.println("扣减库存失败，库存不足");
        }
    }


}
