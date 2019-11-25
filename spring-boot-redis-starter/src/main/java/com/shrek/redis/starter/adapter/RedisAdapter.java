package com.shrek.redis.starter.adapter;


import com.shrek.redis.starter.base.AbstractRedisProcessor;
import com.shrek.redis.starter.constants.RedisModeConstants;
import com.shrek.redis.starter.handler.RedisClusterProcessor;
import com.shrek.redis.starter.handler.RedisSentinelProcessor;
import com.shrek.redis.starter.handler.RedisSingleProcessor;
import com.shrek.redis.starter.util.RedisBeanUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * redis适配器，适配redis各模式处理类
 * 在初始化注入到beanFactory工厂中 见 @RedisConfigRegistrar类
 * @author 吴署
 */
@Slf4j
public class RedisAdapter {

	private String redisMode;


	public RedisAdapter() {
	}

	public String getRedisMode() {
		return redisMode;
	}

	public void setRedisMode(String redisMode) {
		this.redisMode = redisMode;
	}

	/**
	 * 获取redis 处理器
	 * 根据 启动类 注解的 redisMode，判断返回哪个redisProcessor
	 *
	 * @return AbstractRedisProcessor
	 */
	public AbstractRedisProcessor getRedisProcessor() {

		switch (redisMode) {
			case RedisModeConstants.REDIS_SINGLE:
				return RedisBeanUtil
						.getBean(RedisSingleProcessor.class);
			case RedisModeConstants.REDIS_CLUSTER:
				return RedisBeanUtil
						.getBean(RedisClusterProcessor.class);
			case RedisModeConstants.REDIS_SENTINEL:
				return RedisBeanUtil
						.getBean(RedisSentinelProcessor.class);
			default:
				log.error("Failed to obtain redis schema instance params {}", redisMode);
				throw new RuntimeException("Failed to obtain redis schema instance");
		}
	}
}
