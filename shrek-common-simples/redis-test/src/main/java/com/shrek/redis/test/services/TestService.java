package com.shrek.redis.test.services;

import com.alibaba.fastjson.JSONObject;
//import com.easy.redis.template.EasyRedisTemplate;
import com.shrek.redis.starter.template.ShrekRedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: 吴署
 * @Date: 2019/11/21 15:56
 * @Description:
 */

@Component
public class TestService {

    @Autowired
    private ShrekRedisTemplate shrekRedisTemplate;


    public void setkeys(String key,String value){
        shrekRedisTemplate.set(key,value);
    }
}
