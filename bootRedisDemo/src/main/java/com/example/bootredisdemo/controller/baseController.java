package com.example.bootredisdemo.controller;

import com.example.bootredisdemo.utils.RedisLockUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * ClassName: baseController
 * Description:
 * date:  2023-03-24  10:50
 *
 * @author lipeiyu
 * @version 1.0
 */
@Controller
@RequestMapping("/redis")
public class baseController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisLockUtils redisUtils;

    private final String  redisLockPrefix = "redisLockTest";


    @PostMapping("/lock/{id}")
    @ResponseBody
    public String redisLockTest(@PathVariable("id")String id){

        Boolean res = redisTemplate.opsForValue().setIfAbsent("redisLockTest:" + id, "1");
        redisTemplate.isExposeConnection();
        return  "success : "+res;
    }

    @PostMapping("/unlock/{id}")
    public void redisUnlockTest(@PathVariable("id")String id){
    }

    // 获得验证码


}