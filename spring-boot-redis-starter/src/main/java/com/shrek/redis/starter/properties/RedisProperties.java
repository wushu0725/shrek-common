package com.shrek.redis.starter.properties;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;

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

    /**
     * redis 单点地址
     */
    private String host = "localhost";

    /**
     * redis 集群地址
     */
    private String cluster;

    /**
     * redis 哨兵主
     */
    private String master;

    /**
     * redis 哨兵从节点
     */
    private String nodes;

    /**
     * redis 端口
     */
    private Integer port = 6379;

    /**
     * redis 数据库
     */
    private Integer database = 0;

    /**
     * redis 密码
     */
    private String password;

    /**
     * redis 读取超时时间
     */
    private Long timeOut = 5000L;

    /**
     * redis 最大连接数
     */
    private Integer maxActive = 200;

    /**
     * redis 获取连接时的最大等待毫秒数
     */
    private Long maxWait = BaseObjectPoolConfig.DEFAULT_MAX_WAIT_MILLIS;

    /**
     * redis 最大空闲连接数
     */
    private Integer maxIdle = GenericObjectPoolConfig.DEFAULT_MAX_IDLE;

    /**
     * redis 最小空闲连接数
     */
    private Integer minIdle = GenericObjectPoolConfig.DEFAULT_MIN_IDLE;


}
