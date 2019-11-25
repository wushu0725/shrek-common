package com.shrek.redis.starter.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 环境
 *
 * @author niuzhiwei
 */
@Getter
@Setter
@ToString
public class RedisEnvironment {

    public static final String DEFAULT_ADAPTER_KEY = "etcRedisAdapter";
    public static final String DEFAULT_PROCESSOR_SINGLE_KEY = "redisSingleProcessor";
    public static final String DEFAULT_PROCESSOR_CLUSTER_KEY = "redisClusterProcessor";
    public static final String DEFAULT_PROCESSOR_SENTINEL_KEY = "redisSentinelProcessor";

    //redisConnectionFactory是否初始化完成，见RedisConfigRegistrar类
    private Boolean redisConnectionFactoryIsInit = Boolean.FALSE;
}
