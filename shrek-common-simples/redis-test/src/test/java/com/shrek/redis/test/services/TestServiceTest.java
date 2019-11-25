package com.shrek.redis.test.services;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class TestServiceTest {


    @Autowired
    private TestService testService;


    @Test
    void setkeys() {
        IntStream.range(1,10).forEach(i->{
            System.out.println(i);
            testService.setkeys(i+"",i+1+"");
        });
    }


}