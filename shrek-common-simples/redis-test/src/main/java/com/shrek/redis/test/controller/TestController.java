package com.shrek.redis.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.shrek.redis.test.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: 吴署
 * @Date: 2019/11/22 11:00
 * @Description:
 */


@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public JSONObject test(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        int i = 100/0;
        return jsonObject;
    }
}
