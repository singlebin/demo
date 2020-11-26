package com.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @Author: wub
 * @Date: 2020/11/26 16:58
 */
@Data
@ConfigurationProperties("spring.redis")
public class RedisConfigProperties {

    private String host;
    private Integer port;
    private String password;
    private Integer database;
    private Integer maxTotal;
    private Integer maxIdle;
    private Integer timeBetweenEvictionRunsMillis;
    private Integer minEvictableIdleTimeMillis;
    private Boolean testOnBorrow;
    private Boolean testWhileIdle;
    private Integer maxWaitMillis;
    private Integer socketTimeout;
    private Integer numTestsPerEvictionRun;
}
