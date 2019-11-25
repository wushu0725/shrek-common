package com.shrek.redis.test;

//import com.easy.redis.annotation.EnableRedis;
//import com.easy.redis.constants.RedisModeConstants;
import com.shrek.redis.starter.annotation.EnableRedis;
import com.shrek.redis.starter.constants.RedisModeConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Auther: 吴署
 * @Date: 2019/11/20 15:10
 * @Description:
 */
@SpringBootApplication
@EnableRedis(value = RedisModeConstants.REDIS_CLUSTER)
public class RedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }
}
