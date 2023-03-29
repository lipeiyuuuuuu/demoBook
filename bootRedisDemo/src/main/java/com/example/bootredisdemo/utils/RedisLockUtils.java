package com.example.bootredisdemo.utils;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: redisUtils
 * Description:
 * date:  2023-03-24  10:49
 *
 * @author lipeiyu
 * @version 1.0
 */
@Component
public class RedisLockUtils {

    @Resource
    private RedisTemplate<String,String> redisTemplate;


    /**
     * 获取redis锁
     *
     * @param path 路径
     * @return boolean
     */
    public boolean getLock(String path){
        boolean locked = false;

        long nowTime = System.currentTimeMillis() / 1000;
        Boolean result = redisTemplate.opsForValue().setIfAbsent(path, String.valueOf(nowTime));

        if(result){
            redisTemplate.expire(path,60, TimeUnit.SECONDS);
            locked  = true;
        }else{
            String value =  redisTemplate.opsForValue().get(path);
            if(StringUtils.isNotBlank(value)){
                long timeValue = Long.valueOf(value);
                if(nowTime - timeValue > 300){
                    redisTemplate.opsForValue().set(path,String.valueOf(nowTime));
                    redisTemplate.expire(path,60,TimeUnit.SECONDS);
                    locked = true;
                }
            }
        }
        return locked;
    }

    /**
     * 重复一定次数获取redis锁
     *
     * @param redisKey   锁名
     * @param value      锁值
     * @param expireTime 过期时间
     * @param totalCount 尝试次数
     * @return boolean
     * @throws InterruptedException 中断异常
     */
    public boolean checkRedisKey(String redisKey,String value,int expireTime,int totalCount) throws InterruptedException {
        boolean result = false;
        boolean checkCache  = true;
        while (checkCache){
            Boolean cacheResult = redisTemplate.opsForValue().setIfAbsent(redisKey,value,expireTime,TimeUnit.SECONDS);
            if(cacheResult){
                checkCache=false;
                result=true;
            }else{
                totalCount--;
                if(totalCount<=0){
                    checkCache=false;
                }else{
                    Thread.sleep(200);
                }
            }

        }
        return result;
    }

    /**
     * 解锁
     *
     * @param path 路径
     */
    public void unlock(String path){
        if(redisTemplate.hasKey(path)){
            redisTemplate.expire(path, 1, TimeUnit.SECONDS);
        }
    }

}
