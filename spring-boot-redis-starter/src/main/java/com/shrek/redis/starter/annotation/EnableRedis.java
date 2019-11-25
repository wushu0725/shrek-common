package com.shrek.redis.starter.annotation;


import com.shrek.redis.starter.constants.RedisModeConstants;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 启用redis注解 默认模式为单点
 *
 * @author niuzhiwei
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RedisConfigRegistrar.class)
public @interface EnableRedis {

    String value() default RedisModeConstants.REDIS_SINGLE;

}
