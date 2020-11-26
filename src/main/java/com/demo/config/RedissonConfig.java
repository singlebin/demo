//package com.demo.config;
//
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.ClassPathResource;
//
//import java.io.IOException;
//
///**
// * @description:
// * @Author: wub
// * @Date: 2020/11/26 15:01
// */
//@Configuration
//public class RedissonConfig {
//
//    @Primary
//    @Bean(name = "redissonClient")
//    public RedissonClient redissonClient() throws IOException {
//        return Redisson.create(Config.fromYAML(new ClassPathResource("redisson-single.yml").getInputStream()));
//    }
//
//    @Bean(destroyMethod = "shutdown")
//    public RedissonClient shutdown(@Qualifier("redissonClient") RedissonClient redissonClient) {
//        return redissonClient;
//    }
//}
