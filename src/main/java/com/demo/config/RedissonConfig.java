package com.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/26 15:01
 */
@Configuration
@EnableConfigurationProperties(RedisConfigProperties.class)
@PropertySource(value = "classpath:/config/redis.yml", factory = YamlPropertySourceFactory.class)
public class RedissonConfig {

    private final RedisConfigProperties properties;

    public RedissonConfig(RedisConfigProperties properties) {
        this.properties = properties;
    }

//    @Primary
//    @Bean(name = "redissonClient")
//    public RedissonClient redissonClient() throws IOException {
//        return Redisson.create(Config.fromYAML(new ClassPathResource("/config/redisson-single.yml").getInputStream()));
//    }
//
//    @Bean(destroyMethod = "shutdown")
//    public RedissonClient shutdown(@Qualifier("redissonClient") RedissonClient redissonClient) {
//        return redissonClient;
//    }

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + properties.getHost() + ":" + properties.getPort());
        return Redisson.create(config);
    }
}
