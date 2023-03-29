package com.example.bootredisdemo.service.impl;

import com.example.bootredisdemo.service.SixNumberVerificationCodeService;
import com.example.bootredisdemo.service.VerificationCodeService;
import com.example.bootredisdemo.utils.RedisLockUtils;
import demobook.common.CommonResultBean;
import demobook.common.Const;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * ClassName: VerificationCodeServiceImpl
 * Description:
 * date:  2023-03-28  11:18
 *
 * @author lipeiyu
 * @version 1.0
 */
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    /**
     * 六位数字验证码生成
     */
    @Autowired
    private SixNumberVerificationCodeService sixNumberVerificationCodeService;

    @Autowired
    private RedisLockUtils redisLockUtils;

    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public CommonResultBean<String> getSixNumberVerificationCode(String id) {
        CommonResultBean<String> response = new CommonResultBean<>();
        response.fail();
        if(StringUtils.isBlank(id)){
            return response.fail("id不可以为空!");
        }
        String lockKey = Const.SIX_NUMBER_VERIFICATION_CODE +":"+id;
        String verificationCode = sixNumberVerificationCodeService.generateVerificationCode();
        if(redisTemplate.opsForValue().setIfAbsent(lockKey,verificationCode,Const.VERIFICATION_CODE_EXPIRE_TIME_OF_SECOND, TimeUnit.SECONDS)){
            return response.success(null,verificationCode);
        }else{
            Long expire = redisTemplate.getExpire(lockKey);
            return response.fail("请"+expire+"秒之后再次获取验证码.");
        }
    }

    @Override
    public CommonResultBean<Integer> VerificateSixNumberCode(String id, String code) {
        CommonResultBean<Integer> response = new CommonResultBean<>();
        //checkData
        if(StringUtils.isBlank(id)){
            return response.fail("id不可以为空");
        }
        if(StringUtils.isBlank(code.trim())){
            return response.fail("验证码不能为空");
        }

        String redisKey = Const.SIX_NUMBER_VERIFICATION_CODE+":"+id;
        Object verificationCodeObj = redisTemplate.opsForValue().get(redisKey);
        Integer verificationCode = null;
        if(verificationCodeObj!=null){
            verificationCode = Integer.valueOf(verificationCodeObj.toString());
        }else{
            return response.fail("验证码已过期");
        }

        if(verificationCode.equals(Integer.valueOf(code))){
            return response.success("验证成功",1);
        }else{
            return response.fail("验证失败");
        }
    }
}