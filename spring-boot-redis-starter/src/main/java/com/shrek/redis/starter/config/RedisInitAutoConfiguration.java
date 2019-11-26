package com.shrek.redis.starter.config;

import com.shrek.redis.starter.template.ShrekRedisTemplate;
import com.shrek.redis.starter.util.RedisBeanUtil;
import com.shrek.redis.starter.util.RedisConnectionFactoryUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.shrek.redis.starter.properties.RedisProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @Auther: 吴署
 * @Date: 2019/11/22 11:44
 * @Description:
 */
@Configuration
@ConditionalOnProperty(prefix = RedisProperties.PREFIX, name = "enable", havingValue = "true")
@EnableConfigurationProperties(value = RedisProperties.class)
public class RedisInitAutoConfiguration {
    /**
     * 初始化 applicationContext 工具类，
     * 当类继承 ApplicationContextAware ，会调用setApplicationContext
     * @author 吴署
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public RedisBeanUtil beanUtil() {
        return new RedisBeanUtil();
    }

    @Bean
    @ConditionalOnMissingBean(ShrekRedisTemplate.class)
    public ShrekRedisTemplate shrekRedisTemplate() {

        return new ShrekRedisTemplate();
    }

    @Bean
    @ConditionalOnMissingBean(JedisConnectionFactory.class)
    public JedisConnectionFactory jedisConnectionFactory(RedisProperties redisProperties) {

        return RedisConnectionFactoryUtil.init(redisProperties);
    }
}
