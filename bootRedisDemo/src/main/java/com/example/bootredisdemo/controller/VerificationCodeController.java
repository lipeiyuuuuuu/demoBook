package com.example.bootredisdemo.controller;

import com.example.bootredisdemo.service.SixNumberVerificationCodeService;
import com.example.bootredisdemo.service.VerificationCodeService;
import com.example.bootredisdemo.utils.RedisLockUtils;
import demobook.common.CommonResultBean;
import demobook.common.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * ClassName: VerificationCodeController
 * Description:
 * date:  2023-03-28  09:08
 *
 * @author lipeiyu
 * @version 1.0
 */
@Controller
@RequestMapping("/VerificationCode")
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;


    /**
     * 得到六个数字验证码
     *
     * @param id id
     * @return {@link CommonResultBean}
     */
    @PostMapping("/getSixNumberVerificationCode/{id}")
    @ResponseBody
    public CommonResultBean getSixNumberVerificationCode(@PathVariable("id")String id){
        return verificationCodeService.getSixNumberVerificationCode(id);
    }

    @PostMapping("/verificateSixNumberCode/{id}/{code}")
    @ResponseBody
    public CommonResultBean verificateSixNumberCode(@PathVariable("id")String id,@PathVariable("code")String code){
        return verificationCodeService.VerificateSixNumberCode(id,code);
    }


}
