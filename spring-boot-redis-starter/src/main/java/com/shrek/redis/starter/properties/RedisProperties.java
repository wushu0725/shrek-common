package com.shrek.redis.starter.properties;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * @Auther: 吴署
 * @Date: 2019/11/22 13:54
 * @Description: redis配置类
 */

@Data
@ConfigurationProperties(prefix = RedisProperties.PREFIX)
@ToString
public class RedisProperties {
    public static final String PREFIX="shrek.redis";

    private String host = "localhost";

    private String cluster;

    private String master;

    private String nodes;

    private Integer port = 6379;

    private Integer database = 0;

    private String password;

    private Long timeOut = 5000L;

    private Integer maxActive = 200;

    private Long maxWait = BaseObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;

    private Integer maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;

    private Integer minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;


}
