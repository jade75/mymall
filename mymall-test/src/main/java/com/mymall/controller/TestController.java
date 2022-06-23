package com.mymall.controller;

import com.mymall.framework.exception.customizedException;
import com.mymall.framework.utils.JwtTokenUtils;
import com.mymall.framework.utils.RedisUtils;
import com.mymall.framework.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/t2")
    public Result t() {
//        int i = 1 / 0;
        if (true)
            throw new customizedException("fucker man");

        return Result.success("fuck maven");
    }

    @Autowired
    private JwtTokenUtils jwtTokenUtils;

    @GetMapping("/token")
    public Result token() {
        String token = jwtTokenUtils.generateToken("test");
        return Result.success("token", token);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisUtils redisUtils;

    @GetMapping("/redis")
    public Result redis() {
        redisTemplate.opsForValue().set("test:set1", "testValue1");
        redisTemplate.opsForSet().add("test:set2", "asdf");
        redisTemplate.opsForHash().put("hash1", "name1", "lms1");
        redisTemplate.opsForHash().put("hash1", "name2", "lms2");
        redisTemplate.opsForHash().put("hash1", "name3", "lms3");

        return Result.success("ok");
    }

    @GetMapping("/redis2")
    public Result redisU2() {

        redisUtils.set("1", new User("test", 9));


        User u = (User) redisUtils.get("1");

        System.out.println(u);

        return Result.success("ok");
    }


}
